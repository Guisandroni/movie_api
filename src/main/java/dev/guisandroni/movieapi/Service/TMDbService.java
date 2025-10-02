package dev.guisandroni.movieapi.Service;

import dev.guisandroni.movieapi.Controller.dto.TMDbGenre;
import dev.guisandroni.movieapi.Controller.dto.TMDbMovie;
import dev.guisandroni.movieapi.Controller.dto.TMDbMovieResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TMDbService {

    private final WebClient webClient;

    public TMDbService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<List<TMDbMovie>> findPopularMovies() {
        return webClient.get()
                .uri("/movie/popular")
                .retrieve()
                .bodyToMono(TMDbMovieResponse.class)
                .map(TMDbMovieResponse::results);
    }

    public Mono<List<TMDbGenre>> findGenres() {
        return webClient.get()
                .uri("/genre/movie/list")
                .retrieve()
                .bodyToMono(GenreResponse.class)
                .map(GenreResponse::genres);
    }

    private record GenreResponse(List<TMDbGenre> genres) {}
}
