package com.pfseven.smdb.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pfseven.smdb.domain.Sitcom;
import java.io.IOException;

public class SitcomSerializer extends  ProductionSerializer<Sitcom>  {

    public SitcomSerializer() {
        this(null);
    }
    public SitcomSerializer(Class<Sitcom> t) {
        super(t);
    }

    @Override
    public  void serialize(Sitcom s, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        super.serialize(s,gen,provider);
        gen.writeNumberField("seasons: ",s.getSeasons());
        gen.writeNumberField("episodes: ", s.getEpisodes());
        gen.writeEndObject();
    }

}

