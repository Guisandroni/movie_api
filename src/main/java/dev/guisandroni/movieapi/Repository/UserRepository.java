package dev.guisandroni.movieapi.Repository;

import dev.guisandroni.movieapi.Entity.User;
import org.apache.catalina.UserDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<UserDetails> findUserByEmail(String email);
    
    Optional<UserDetails> deleteByEmail(String email);

    boolean existsByEmail(String email);

}
