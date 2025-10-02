package dev.guisandroni.movieapi.Controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TMDbGenre(
        long id,
        String name
) {
}
