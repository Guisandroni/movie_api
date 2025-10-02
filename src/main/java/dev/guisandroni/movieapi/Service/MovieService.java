package dev.guisandroni.movieapi.Service;


import dev.guisandroni.movieapi.Entity.Category;
import dev.guisandroni.movieapi.Entity.Movie;
import dev.guisandroni.movieapi.Entity.Streaming;
import dev.guisandroni.movieapi.Repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.config.RepositoryConfigurationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    
    private  final MovieRepository repository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;
    
    
    
    public List<Movie> findAll(){
        return repository.findAll();
    }
    

    public Long findNumberOfMovies(){
        return repository.count();
    }

    public Long countMovies() {
        return repository.count();
    }
    
    
    public boolean existsMovieTitle(Movie movie){
        String movieTitle = movie.getTitle();
        return repository.existsByTitle(movieTitle);
    
    
    }
    
    
    public Movie created (Movie movie){
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        
        return repository.save(movie);
    }
    
    
    public Optional<Movie> findById(Long id){
        return repository.findById(id);
    }
    
    
    public Optional<Movie> update(Long id, Movie newMovie){
        Optional<Movie> movieId = repository.findById(id);
        
        if(movieId.isPresent()){
            
            List<Category> categories = this.findCategories(newMovie.getCategories());
            List<Streaming> streamings = this.findStreamings(newMovie.getStreamings());
            
            Movie oldMovie = movieId.get();
            
            oldMovie.setTitle(newMovie.getTitle());
            oldMovie.setDescription(newMovie.getDescription());
            oldMovie.setRelease_date(newMovie.getRelease_date());
            oldMovie.setRating(newMovie.getRating());
            oldMovie.setCategories(newMovie.getCategories());
            oldMovie.setStreamings(newMovie.getStreamings());
            
            oldMovie.setCategories(categories);
            oldMovie.setStreamings(streamings);
            
            repository.save(oldMovie);
            return Optional.of(oldMovie);
        }
        
        return Optional.empty();
    }
    
    
    
    public void deleteByMovie(Long id){
        repository.deleteById(id);
    }
    
    public Boolean checkIdToDelete(Long id){
        return repository.existsById(id);
    }


    public List<Category> findCategories (List<Category> categories){
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category -> categoryService.findById(category.getId()).ifPresent(categoriesFound::add));
        return categoriesFound;
    }

    public List<Streaming> findStreamings (List<Streaming> streamings){
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming -> streamingService.findById(streaming.getId()).ifPresent(streamingsFound::add));
        return streamingsFound;
    }
    
    public List<Movie> findByCategories (Long Categoryid){
        return repository.findMovieByCategories(List.of(Category.builder().id(Categoryid).build()));   
    }
    
    
    
}
