package com.kbje.controller;

import com.kbje.service.CategoryService;
import com.kbje.service.JsonFileWatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class MainController {
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private JsonFileWatcherService jsonFileWatcherService;
    
    @GetMapping("/")
    public String index(Model model) {
        List<CategoryService.Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("availableFiles", jsonFileWatcherService.getAvailableFiles());
        return "index";
    }
    
    @GetMapping("/category/{categoryName}")
    public String categoryDetail(@PathVariable String categoryName, Model model) {
        CategoryService.Category category = categoryService.getCategory(categoryName);
        if (category != null) {
            model.addAttribute("category", category);
            return "category-detail";
        }
        return "redirect:/";
    }
    
    @GetMapping("/api/categories")
    @ResponseBody
    public List<CategoryService.Category> getCategories() {
        return categoryService.getAllCategories();
    }
    
    @GetMapping("/api/categories/{categoryName}")
    @ResponseBody
    public CategoryService.Category getCategory(@PathVariable String categoryName) {
        return categoryService.getCategory(categoryName);
    }
    
    @GetMapping("/api/files")
    @ResponseBody
    public Set<String> getAvailableFiles() {
        return jsonFileWatcherService.getAvailableFiles();
    }
    
    @GetMapping("/api/status")
    @ResponseBody
    public String getStatus() {
        return "System is running. Found " + categoryService.getAllCategories().size() + " categories.";
    }
} 