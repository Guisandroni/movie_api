package dev.guisandroni.movieapi.Controller;


import dev.guisandroni.movieapi.Controller.Request.UserRequest;
import dev.guisandroni.movieapi.Controller.Response.LoginResponse;
import dev.guisandroni.movieapi.Controller.Response.UserResponse;
import dev.guisandroni.movieapi.Entity.User;
import dev.guisandroni.movieapi.Mapper.UserMapper;
import dev.guisandroni.movieapi.Service.UserService;
import dev.guisandroni.movieapi.config.TokenService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/movieapi/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UserService service;
    private final AuthenticationManager authenticateManager;
    private final TokenService tokenService;
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register (@RequestBody UserRequest request){
        User savedUser = service.created(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(savedUser));
    }


    @PostMapping ("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody UserRequest request){
        //existe forma manual e utilizando metodos do spring
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(request.email(),request.password());
        Authentication authentication = authenticateManager.authenticate(userPass);

        User user = (User)authentication.getPrincipal();

        //gerar token

        String token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
}

@DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
         service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
}

@GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> users = service.findAll()
                .stream()
                .map(user->UserMapper.toUserResponse(user))
                .toList();
        return ResponseEntity.ok(users);
}
}
