package dev.guisandroni.movieapi.Controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TMDbMovie(
        long id,
        String title,
        @JsonProperty("original_title") String originalTitle,
        String overview,
        @JsonProperty("poster_path") String posterPath,
        @JsonProperty("release_date") String releaseDate,
        @JsonProperty("genre_ids") List<Long> genreIds
) {
}
