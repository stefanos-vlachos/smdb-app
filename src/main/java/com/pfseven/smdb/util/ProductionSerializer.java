package com.pfseven.smdb.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.pfseven.smdb.domain.ContributorProduction;
import com.pfseven.smdb.domain.Production;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        gen.writeObjectField("release year: ", m.getReleaseYear());
        gen.writeNumberField("rating: ",m.getRating());
        gen.writeStringField("language: ",m.getLanguage());
        gen.writeObjectField("genres: " , m.getGenres());
        gen.writeStringField("resume: " , m.getResume());


        gen.writeArrayFieldStart("contributors: ");
        for(ContributorProduction c: m.getContributorProductions()){
            gen.writeStartObject();
            gen.writeObjectField("contributorId" , c.getContributor().getId());
            gen.writeObjectField("contributorName" , c.getContributor().getFullName());
            gen.writeObjectField("role" , c.getRole().toString() );
            gen.writeEndObject();
        }
        gen.writeEndArray();

    }
}
