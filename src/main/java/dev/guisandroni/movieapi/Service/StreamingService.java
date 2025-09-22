package dev.guisandroni.movieapi.Service;


import dev.guisandroni.movieapi.Controller.Response.StreamingResponse;
import dev.guisandroni.movieapi.Entity.Streaming;
import dev.guisandroni.movieapi.Mapper.StreamingMapper;
import dev.guisandroni.movieapi.Repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {
    
    private final StreamingRepository repository;
    
    public List<Streaming> findAll (){
        return repository.findAll();
    }
    
    public Streaming created (Streaming streaming) {
        return repository.save(streaming);
    }
    
    public Optional<Streaming> findById(Long id){
        return repository.findById(id);
    }
    
   public StreamingResponse update ( Streaming updateStreaming, Long id){
        Optional<Streaming> streamingId = repository.findById(id);
        streamingId.orElseThrow(()-> new RuntimeException("Streaming com o id " + id + " não encontrando"));
        Streaming oldStreaming = streamingId.get();
        return StreamingMapper.toStreamingResponse(repository.save(updateStreaming));
   }
   
   public void delete (Long id){
        repository.deleteById(id);
   }
   
   public Boolean checkIdToDelte(Long id){
        return repository.existsById(id);
   }
}
