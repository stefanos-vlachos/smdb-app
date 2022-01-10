package com.pfseven.smdb.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.pfseven.smdb.domain.ContributorProduction;
import com.pfseven.smdb.domain.Production;
import java.io.IOException;

public abstract class  ProductionSerializer<T extends Production> extends StdSerializer<T> {

    public ProductionSerializer() {
        this(null);
    }
    public ProductionSerializer(Class<T> t) {
        super(t);
    }

    @Override
    public  void serialize(T m, JsonGenerator gen, SerializerProvider provider)
            throws IOException {

        gen.writeStartObject();
        gen.writeStringField("title: ",m.getTitle());
        gen.writeNumberField("releaseYear: ", m.getReleaseYear());
        gen.writeNumberField("rating: ",m.getRating());
        gen.writeStringField("language: ",m.getLanguage());
        gen.writeObjectField("genres: " , m.getGenres());
        gen.writeStringField("resume: " , m.getResume());

        gen.writeArrayFieldStart("contributorProductions: ");
        for(ContributorProduction c: m.getContributorProductions()){
            gen.writeStartObject();
            gen.writeObjectField("id" , c.getContributor().getId());
            gen.writeObjectField("fullName" , c.getContributor().getFullName());
            gen.writeObjectField("role" , c.getRole().toString() );
            gen.writeEndObject();
        }
        gen.writeEndArray();

    }
}
