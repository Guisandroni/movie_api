package dev.guisandroni.movieapi.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {
    
    @Id
    private Long id;
    @Column (length = 100, nullable = false)
    private String name;
  
    
    
    
}
