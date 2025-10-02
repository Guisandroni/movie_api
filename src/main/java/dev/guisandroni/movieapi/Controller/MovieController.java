package dev.guisandroni.movieapi.Controller;


import dev.guisandroni.movieapi.Controller.Request.MovieRequest;
import dev.guisandroni.movieapi.Controller.Response.MovieResponse;
import dev.guisandroni.movieapi.Entity.Movie;
import dev.guisandroni.movieapi.Mapper.MovieMapper;
import dev.guisandroni.movieapi.Service.MovieService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.flywaydb.core.internal.publishing.PublishingConfigurationExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movieapi/movie")
public class MovieController {
    
    private final MovieService service;
    
    
    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        List<MovieResponse> movies = service.findAll()
                .stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList();
        return ResponseEntity.ok(movies);
    }
    
    public ResponseEntity<Long> getNumberOfMovies(){
        return ResponseEntity.ok(service.findNumberOfMovies());
    }
    
    @PostMapping
    public ResponseEntity<MovieResponse> saveMovie(@RequestBody MovieRequest request){
        Movie newMovie = MovieMapper.toMovie(request);
        if(service.existsMovieTitle(newMovie)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if(newMovie != null) {
            Movie savedMovie = service.created(newMovie);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(MovieMapper.toMovieResponse(savedMovie));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie (@PathVariable Long id, @RequestBody MovieRequest request){
        return service.update(id, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id){
        return service.findById(id).map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByMovie(@PathVariable Long id){
        if (!service.checkIdToDelete(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        service.deleteByMovie(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



    //formato da url localhost:8080/movieapi/movie/search?category=1 
    
    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category){
        return ResponseEntity.ok(service.findByCategories(category)
                .stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList());
    }
    
}
