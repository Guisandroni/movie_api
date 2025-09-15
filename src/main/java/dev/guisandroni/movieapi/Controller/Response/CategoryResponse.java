package dev.guisandroni.movieapi.Controller.Response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name) {
}
