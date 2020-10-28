package com.gamla.admin.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by boris on 8/18/17.
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String formatedDate = format.format(date);

        jsonGenerator.writeString(formatedDate);
    }
}
