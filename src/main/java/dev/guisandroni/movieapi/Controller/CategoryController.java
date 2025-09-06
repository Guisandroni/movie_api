package dev.guisandroni.movieapi.Controller;


import dev.guisandroni.movieapi.Controller.Request.CategoryRequest;
import dev.guisandroni.movieapi.Controller.Response.CategoryResponse;
import dev.guisandroni.movieapi.Entity.Category;
import dev.guisandroni.movieapi.Mapper.CategoryMapper;
import dev.guisandroni.movieapi.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/movieapi/category")
@RequiredArgsConstructor
public class CategoryController {
    
    private final CategoryService service;
    
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategorys(){
            List<CategoryResponse> categories = service.findAll()
                    .stream()
                    .map(category -> CategoryMapper.toCategoryResponse(category))
                    .toList();
            return ResponseEntity.ok(categories);
    }
    
    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request){
        
        Category newCategory = CategoryMapper.toCategory(request);
        if(newCategory!=null) {
            Category savedCategory = service.created(newCategory);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CategoryMapper.toCategoryResponse(savedCategory));    
            }
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        
    }
    
//    public getCategoryById(){}
//    
//    public updateCategory(){}
//    
//    public deleteCategory(){}
    
    }
