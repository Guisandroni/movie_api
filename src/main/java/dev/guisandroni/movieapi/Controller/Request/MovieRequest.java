package dev.guisandroni.movieapi.Controller.Request;


import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder    
public record MovieRequest (
        String title,
        String description,
        LocalDate release_date,
        double rating,
        List<Long> categories,
        List<Long> streamings
        
) {
}
