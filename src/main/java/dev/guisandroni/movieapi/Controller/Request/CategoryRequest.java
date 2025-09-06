package dev.guisandroni.movieapi.Controller.Request;

import lombok.Builder;

@Builder
public record CategoryRequest(String title) {

}
