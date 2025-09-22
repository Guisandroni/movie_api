package dev.guisandroni.movieapi.Mapper;


import dev.guisandroni.movieapi.Controller.Request.StreamingRequest;
import dev.guisandroni.movieapi.Controller.Response.StreamingResponse;
import dev.guisandroni.movieapi.Entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming (StreamingRequest request){
        return Streaming
                .builder()
                .name(request.name())
                .build();
    }
    
    public static StreamingResponse toStreamingResponse (Streaming streaming){
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
