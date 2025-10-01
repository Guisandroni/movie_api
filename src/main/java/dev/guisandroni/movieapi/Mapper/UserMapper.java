package dev.guisandroni.movieapi.Mapper;


import dev.guisandroni.movieapi.Controller.Request.UserRequest;
import dev.guisandroni.movieapi.Controller.Response.UserResponse;
import dev.guisandroni.movieapi.Entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    
    public static User toUser(UserRequest request){
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }
    
    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
