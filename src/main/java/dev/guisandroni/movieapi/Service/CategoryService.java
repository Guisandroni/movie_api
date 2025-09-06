package dev.guisandroni.movieapi.Service;


import dev.guisandroni.movieapi.Entity.Category;
import dev.guisandroni.movieapi.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    
    private final CategoryRepository repository;
    
    public List<Category> findAll(){
        return repository.findAll();
    }
    
    public Category created (Category category){
        return repository.save(category);
    }
    
    
}
