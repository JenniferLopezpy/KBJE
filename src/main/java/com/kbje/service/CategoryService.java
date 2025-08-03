package com.kbje.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@ApplicationScope
public class CategoryService {
    
    private final Map<String, Category> categories = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public void updateCategoriesFromJson(String fileName, JsonNode jsonNode) {
        try {
            // Extract category name from file name (remove .json extension)
            String categoryName = fileName.replaceAll("\\.json$", "");
            
            // Create category object
            Category category = new Category();
            category.setName(categoryName);
            category.setFileName(fileName);
            category.setLastUpdated(new Date());
            
            // Parse JSON structure to find subcategories
            List<Subcategory> subcategories = parseSubcategories(jsonNode);
            category.setSubcategories(subcategories);
            
            // Store the category
            categories.put(categoryName, category);
            
            System.out.println("Updated category: " + categoryName + " with " + subcategories.size() + " subcategories");
            
        } catch (Exception e) {
            System.err.println("Error updating categories from JSON: " + e.getMessage());
        }
    }
    
    private List<Subcategory> parseSubcategories(JsonNode jsonNode) {
        List<Subcategory> subcategories = new ArrayList<>();
        
        if (jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String key = entry.getKey();
                JsonNode value = entry.getValue();
                
                Subcategory subcategory = new Subcategory();
                subcategory.setName(key);
                subcategory.setContent(parseContent(value));
                subcategories.add(subcategory);
            }
        }
        
        return subcategories;
    }
    
    private String parseContent(JsonNode node) {
        if (node.isTextual()) {
            return node.asText();
        } else if (node.isObject() || node.isArray()) {
            return node.toPrettyString();
        } else {
            return node.toString();
        }
    }
    
    public void removeCategory(String fileName) {
        String categoryName = fileName.replaceAll("\\.json$", "");
        categories.remove(categoryName);
        System.out.println("Removed category: " + categoryName);
    }
    
    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }
    
    public Category getCategory(String categoryName) {
        return categories.get(categoryName);
    }
    
    public List<Subcategory> getSubcategories(String categoryName) {
        Category category = categories.get(categoryName);
        return category != null ? category.getSubcategories() : new ArrayList<>();
    }
    
    public Set<String> getCategoryNames() {
        return new HashSet<>(categories.keySet());
    }
    
    // Data classes
    public static class Category {
        private String name;
        private String fileName;
        private List<Subcategory> subcategories;
        private Date lastUpdated;
        
        // Getters and Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getFileName() { return fileName; }
        public void setFileName(String fileName) { this.fileName = fileName; }
        
        public List<Subcategory> getSubcategories() { return subcategories; }
        public void setSubcategories(List<Subcategory> subcategories) { this.subcategories = subcategories; }
        
        public Date getLastUpdated() { return lastUpdated; }
        public void setLastUpdated(Date lastUpdated) { this.lastUpdated = lastUpdated; }
    }
    
    public static class Subcategory {
        private String name;
        private String content;
        
        // Getters and Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }
} 