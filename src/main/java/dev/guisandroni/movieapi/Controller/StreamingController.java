package dev.guisandroni.movieapi.Controller;


import dev.guisandroni.movieapi.Controller.Request.StreamingRequest;
import dev.guisandroni.movieapi.Controller.Response.StreamingResponse;
import dev.guisandroni.movieapi.Entity.Streaming;
import dev.guisandroni.movieapi.Mapper.StreamingMapper;
import dev.guisandroni.movieapi.Service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieapi/streaming")
@RequiredArgsConstructor
public class StreamingController {
    
    
    private final StreamingService service;
    
    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAllStreamings(){
        List<StreamingResponse> streamings = service.findAll()
                .stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList();
        
        return ResponseEntity.ok(streamings);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity <StreamingResponse> getStreamingById (@PathVariable Long id){
        return service.findById(id).map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<StreamingResponse> saveStreaming (@RequestBody StreamingRequest request){
        Streaming newStreaming = StreamingMapper.toStreaming(request);
        if(newStreaming != null){
            Streaming savedStreaming = service.created(newStreaming);
            return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(savedStreaming));
        }
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        
        
        
    }
    
        
    
    @PutMapping("/{id}")
    public ResponseEntity<StreamingResponse> updateStreaming (@PathVariable Long id, @RequestBody StreamingRequest request){
        Streaming updateStreaming = StreamingMapper.toStreaming(request);
        StreamingResponse updateToStreaming = service.update(updateStreaming, id);
        if(updateToStreaming != null){
            return ResponseEntity.ok(updateToStreaming);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByStreaming (@PathVariable Long id){
        if (!service.checkIdToDelte(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            
        }
        
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
