package dev.guisandroni.movieapi.Repository;

import dev.guisandroni.movieapi.Entity.Category;
import dev.guisandroni.movieapi.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsByTitle(String title);
    
    List<Movie> findMovieByCategories (List<Category> categories);
    
    List<Movie> findTop5ByOrderByRatingDesc();
}
