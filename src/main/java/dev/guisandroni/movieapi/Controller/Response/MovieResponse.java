package dev.guisandroni.movieapi.Controller.Response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(
        Long id,
        String title,
        String description,
        double rating,
        LocalDate release_date,
        List<CategoryResponse> categories,
        List <StreamingResponse> streamings
) {
}
