package com.pfseven.smdb.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.pfseven.smdb.domain.Contributor;
import com.pfseven.smdb.domain.ContributorProduction;
import java.io.IOException;
import java.util.Locale;

public class ContributorSerializer extends StdSerializer<Contributor> {

    public ContributorSerializer() {
        this(null);
    }

    public ContributorSerializer(Class<Contributor> t) {
        super(t);
    }

    @Override
    public void serialize(Contributor c, JsonGenerator gen, SerializerProvider provider)
            throws IOException {

        gen.writeStartObject();
        gen.writeStringField("fullName: ",c.getFullName());
        gen.writeStringField("Gender: ",c.getGender());
        gen.writeStringField("Origin: ",c.getOrigin());

        gen.writeArrayFieldStart("contributorProductions: ");
        for(ContributorProduction cp: c.getContributorProductions()){
            gen.writeStartObject();
            gen.writeObjectField("productionId" , cp.getProduction().getId());
            gen.writeObjectField("productionTitle" , cp.getProduction().getTitle());
            gen.writeObjectField("role" , cp.getRole().toString() );
            gen.writeEndObject();
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }



}
