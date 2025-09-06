package dev.guisandroni.movieapi.Mapper;


import dev.guisandroni.movieapi.Controller.Request.CategoryRequest;
import dev.guisandroni.movieapi.Controller.Response.CategoryResponse;
import dev.guisandroni.movieapi.Entity.Category;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CategoryMapper {
    
    public static Category toCategory(CategoryRequest request){
        return Category
                .builder()
                .title(request.title())
                .build();
    }
    
    
    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .build();
        
    }
}
