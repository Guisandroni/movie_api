package dev.guisandroni.movieapi.Mapper;


import dev.guisandroni.movieapi.Controller.Request.MovieRequest;
import dev.guisandroni.movieapi.Controller.Response.CategoryResponse;
import dev.guisandroni.movieapi.Controller.Response.MovieResponse;
import dev.guisandroni.movieapi.Controller.Response.StreamingResponse;
import dev.guisandroni.movieapi.Entity.Category;
import dev.guisandroni.movieapi.Entity.Movie;
import dev.guisandroni.movieapi.Entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {
    
    public static Movie toMovie (MovieRequest request){
        List<Category> categories = request
                .categories()
                .stream()
                .map(categoryId -> Category.builder().id(categoryId).build()).toList();
        
        List<Streaming> streamings = request
                .streamings()
                .stream()
                .map(streamingId-> Streaming.builder().id(streamingId).build()).toList();
                
        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .release_date(request.release_date())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                        .build();
    }

    
    public static MovieResponse toMovieResponse (Movie movie){
    
        List<CategoryResponse> categories = movie.
                getCategories()
                .stream()
                .map(category -> CategoryMapper.toCategoryResponse(category)).toList();
    
        
        List<StreamingResponse> streamings = movie
                .getStreamings()
                .stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming)).toList();
        return MovieResponse
                .builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .release_date(movie.getRelease_date())   
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
}
