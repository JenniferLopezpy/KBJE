package com.kbje.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@ApplicationScope
public class JsonFileWatcherService {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, JsonNode> jsonDataCache = new ConcurrentHashMap<>();
    private final Map<String, Long> fileLastModified = new ConcurrentHashMap<>();
    private FileAlterationMonitor monitor;
    
    @Autowired
    private CategoryService categoryService;
    
    private static final String JSON_DIRECTORY = ".";
    private static final String JSON_EXTENSION = ".json";
    
    @PostConstruct
    public void initialize() {
        try {
            // Load existing JSON files
            loadExistingJsonFiles();
            
            // Start file monitoring
            startFileMonitoring();
            
            System.out.println("JSON File Watcher initialized successfully");
        } catch (Exception e) {
            System.err.println("Error initializing JSON File Watcher: " + e.getMessage());
        }
    }
    
    private void loadExistingJsonFiles() {
        try {
            File directory = new File(JSON_DIRECTORY);
            File[] jsonFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(JSON_EXTENSION));
            
            if (jsonFiles != null) {
                for (File file : jsonFiles) {
                    processJsonFile(file);
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading existing JSON files: " + e.getMessage());
        }
    }
    
    private void startFileMonitoring() throws Exception {
        File directory = new File(JSON_DIRECTORY);
        FileAlterationObserver observer = new FileAlterationObserver(directory, pathname -> 
            pathname.getName().toLowerCase().endsWith(JSON_EXTENSION));
        
        observer.addListener(new FileAlterationListenerAdaptor() {
            @Override
            public void onFileCreate(File file) {
                System.out.println("New JSON file detected: " + file.getName());
                processJsonFile(file);
            }
            
            @Override
            public void onFileChange(File file) {
                System.out.println("JSON file modified: " + file.getName());
                processJsonFile(file);
            }
            
            @Override
            public void onFileDelete(File file) {
                System.out.println("JSON file deleted: " + file.getName());
                removeJsonFile(file.getName());
            }
        });
        
        monitor = new FileAlterationMonitor(1000); // Check every second
        monitor.addObserver(observer);
        monitor.start();
    }
    
    private void processJsonFile(File file) {
        try {
            String fileName = file.getName();
            long lastModified = file.lastModified();
            
            // Check if file has actually changed
            Long cachedLastModified = fileLastModified.get(fileName);
            if (cachedLastModified != null && cachedLastModified >= lastModified) {
                return; // File hasn't changed
            }
            
            // Read and parse JSON file
            String content = new String(Files.readAllBytes(file.toPath()));
            JsonNode jsonNode = objectMapper.readTree(content);
            
            // Cache the data
            jsonDataCache.put(fileName, jsonNode);
            fileLastModified.put(fileName, lastModified);
            
            // Update categories
            categoryService.updateCategoriesFromJson(fileName, jsonNode);
            
            System.out.println("Successfully processed JSON file: " + fileName);
            
        } catch (IOException e) {
            System.err.println("Error processing JSON file " + file.getName() + ": " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error processing JSON file " + file.getName() + ": " + e.getMessage());
        }
    }
    
    private void removeJsonFile(String fileName) {
        jsonDataCache.remove(fileName);
        fileLastModified.remove(fileName);
        categoryService.removeCategory(fileName);
        System.out.println("Removed JSON file from cache: " + fileName);
    }
    
    public Map<String, JsonNode> getAllJsonData() {
        return new HashMap<>(jsonDataCache);
    }
    
    public JsonNode getJsonData(String fileName) {
        return jsonDataCache.get(fileName);
    }
    
    public Set<String> getAvailableFiles() {
        return new HashSet<>(jsonDataCache.keySet());
    }
    
    @PreDestroy
    public void cleanup() {
        if (monitor != null) {
            try {
                monitor.stop();
            } catch (Exception e) {
                System.err.println("Error stopping file monitor: " + e.getMessage());
            }
        }
    }
} 