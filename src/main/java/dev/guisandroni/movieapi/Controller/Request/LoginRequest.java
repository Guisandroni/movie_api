package dev.guisandroni.movieapi.Controller.Request;

import lombok.Builder;

@Builder
public record LoginRequest (String email, String password){
}
