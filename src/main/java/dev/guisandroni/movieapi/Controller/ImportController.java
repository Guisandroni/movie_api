package dev.guisandroni.movieapi.Controller;

import dev.guisandroni.movieapi.Entity.Category;
import dev.guisandroni.movieapi.Entity.Movie;
import dev.guisandroni.movieapi.Repository.CategoryRepository;
import dev.guisandroni.movieapi.Service.CategoryService;
import dev.guisandroni.movieapi.Service.MovieService;
import dev.guisandroni.movieapi.Service.TMDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movieapi/import")
@RequiredArgsConstructor
public class ImportController {

    private final TMDbService tmdbService;
    private final MovieService movieService;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @PostMapping("/movies")
    public ResponseEntity<String> importMovies() {
        List<Category> tmdbCategories = tmdbService.findGenres().block().stream()
                .map(g -> new Category(g.id(), g.name()))
                .collect(Collectors.toList());

        categoryRepository.saveAll(tmdbCategories);

        tmdbService.findPopularMovies().block().forEach(tmdbMovie -> {
            Movie movie = new Movie();
            movie.setTitle(tmdbMovie.title());
            movie.setDescription(tmdbMovie.overview());
           // movie.setReleaseYear(Integer.parseInt(tmdbMovie.releaseDate().substring(0, 4)));

            List<Category> categories = tmdbMovie.genreIds().stream()
                    .map(id -> categoryRepository.findById(id).orElse(null))
                    .filter(c -> c != null)
                    .collect(Collectors.toList());

            movie.setCategories(categories);

            movieService.created(movie);
        });

        return ResponseEntity.ok("Movies imported successfully.");
    }
}
