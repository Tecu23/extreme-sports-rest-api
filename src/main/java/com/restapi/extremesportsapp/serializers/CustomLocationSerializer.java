package com.restapi.extremesportsapp.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.restapi.extremesportsapp.entity.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomLocationSerializer extends StdSerializer<List<Location>> {

    public CustomLocationSerializer(){this(null);}

    protected CustomLocationSerializer(Class<List<Location>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Location> locations, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        List<Location> lc = new ArrayList<>();

        for(Location l: locations){
            l.setSports(null);
            lc.add(l);
        }
        jsonGenerator.writeObject(lc);
    }
}
