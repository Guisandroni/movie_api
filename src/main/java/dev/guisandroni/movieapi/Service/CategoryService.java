package dev.guisandroni.movieapi.Service;


import dev.guisandroni.movieapi.Controller.Response.CategoryResponse;
import dev.guisandroni.movieapi.Entity.Category;
import dev.guisandroni.movieapi.Mapper.CategoryMapper;
import dev.guisandroni.movieapi.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    
    public Optional<Category> findById(Long id){
        return repository.findById(id);
    }
    
    public CategoryResponse update(Category updateCategory,Long id){
        Optional<Category> categoryId = repository.findById(id);
        categoryId.orElseThrow(()-> new RuntimeException("Categoria com o id " + id +" não existe"));
        Category oldCategory = categoryId.get();
        return CategoryMapper.toCategoryResponse(repository.save(updateCategory));
    }
    
    public void delete(Long id){
         repository.deleteById(id);
    }
    
    public Boolean checkIdToDelete(Long id){
        return repository.existsById(id);
    }
}
