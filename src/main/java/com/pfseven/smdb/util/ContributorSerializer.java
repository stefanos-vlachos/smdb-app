package com.pfseven.smdb.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.pfseven.smdb.domain.Contributor;
import com.pfseven.smdb.domain.ContributorProduction;
import java.io.IOException;
import java.util.*;

public class ContributorSerializer extends StdSerializer<Contributor> {

    public ContributorSerializer() {
        this(null);
    }

    public ContributorSerializer(Class<Contributor> t) {
        super(t);
    }

    @Override
    public void serialize(Contributor cp, JsonGenerator gen, SerializerProvider provider)
            throws IOException {

        gen.writeStartObject();
        gen.writeStringField("fullName: ",cp.getFullName());
        gen.writeStringField("Gender: ",cp.getGender());

        List<String> contributions = new ArrayList<>();

        for(ContributorProduction c: cp.getContributorProductions()){
            contributions.add(c.getProduction().getTitle() + "(" + c.getProduction().getClass().getSimpleName()+  ")" + " : " + c.getRole().toString() );
        }
        gen.writeObjectField("Contributions: " , contributions);
        gen.writeEndObject();
    }



}
