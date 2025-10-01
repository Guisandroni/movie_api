package dev.guisandroni.movieapi.Service;

import dev.guisandroni.movieapi.Entity.User;
import dev.guisandroni.movieapi.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    
    public User created(User user) {
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        return repository.save(user);
    }
    
   
    public void delete ( Long id) {
        repository.deleteById(id);
    }

    
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
    
    public List<User> findAll (){
        return repository.findAll();
    }
    
}
