package dev.guisandroni.movieapi.Controller.Response;


import lombok.Builder;

@Builder
public record LoginResponse (String token){
}
