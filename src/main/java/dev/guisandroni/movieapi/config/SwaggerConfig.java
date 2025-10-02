package dev.guisandroni.movieapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI getOpenAPI(){
        Info info = new Info();
        info.title("Movie API");
        info.version("v1");
        info.description("application for movie management");
        
        
        
        return new OpenAPI().info(info);
    }
}
