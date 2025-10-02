package dev.guisandroni.movieapi.Controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TMDbMovieResponse(
        List<TMDbMovie> results
) {
}
