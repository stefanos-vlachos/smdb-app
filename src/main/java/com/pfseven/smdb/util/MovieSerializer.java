package com.pfseven.smdb.util;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pfseven.smdb.domain.Movie;
import java.io.IOException;



public class MovieSerializer extends ProductionSerializer<Movie> {

    public MovieSerializer() {
        this(null);
    }
    public MovieSerializer(Class<Movie> t) {
        super(t);
    }

    @Override
    public  void serialize(Movie m, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        super.serialize(m,gen,provider);
        gen.writeNumberField("duration: ",m.getDuration());
        gen.writeEndObject();
    }

}
