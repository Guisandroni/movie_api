package dev.guisandroni.movieapi.Controller.Request;

import lombok.Builder;

@Builder
public record UserRequest (String name, String email, String password){
}
