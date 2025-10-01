package dev.guisandroni.movieapi.Controller.Response;

import lombok.Builder;

@Builder
public record UserResponse (Long id, String name, String email, String password){
}
