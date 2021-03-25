package com.restapi.extremesportsapp.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.restapi.extremesportsapp.entity.Sport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomSportSerializer extends StdSerializer<List<Sport>> {

    public CustomSportSerializer(){
        this(null);
    }

    public CustomSportSerializer(Class<List<Sport>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Sport> sports, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        List<Sport> sp = new ArrayList<>();

        for(Sport s : sports){
            s.setLocations(null);
            sp.add(s);
        }
        jsonGenerator.writeObject(sp);
    }
}
