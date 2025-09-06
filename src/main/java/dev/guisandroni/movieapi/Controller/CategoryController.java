package dev.guisandroni.movieapi.Controller;


import dev.guisandroni.movieapi.Entity.Category;
import dev.guisandroni.movieapi.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieapi/category")
@RequiredArgsConstructor
public class CategoryController {
    
    private final CategoryService service;
    
    @GetMapping
    public getAllCategorys(){}
    
    public saveCategory(){}
    
    public getCategoryById(){}
    
    public updateCategory(){}
    
    public deleteCategory(){}
    
    }
