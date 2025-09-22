package dev.guisandroni.movieapi.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name ="movie")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    private String description;
    
    @Column(name = "release_date")
    private LocalDate release_date;
    
    private double rating;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    
    //relação entre as tabelas
    
    @ManyToMany
    @JoinTable(
            name = "movie_category", 
            joinColumns = 
            @JoinColumn(name = "movie_id"),
            inverseJoinColumns = 
                    @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
    
    @ManyToMany
    @JoinTable(
            name = "movie_streaming",
            joinColumns = 
            @JoinColumn(name = "movie_id"),
            inverseJoinColumns = 
                    @JoinColumn(name = "streaming_id")
    )
    private List<Streaming> streamings;
    
    
}
