package dev.guisandroni.movieapi.Repository;

import dev.guisandroni.movieapi.Entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StreamingRepository extends JpaRepository<Streaming, Long> {
}
