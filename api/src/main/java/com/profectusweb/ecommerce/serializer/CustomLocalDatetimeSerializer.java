package com.profectusweb.ecommerce.serializer;

import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Configuration
public class CustomLocalDatetimeSerializer extends StdSerializer<LocalDateTime> {

    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    public CustomLocalDatetimeSerializer() {
        this(null);
    }

    public CustomLocalDatetimeSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(
            LocalDateTime localDateTime,
            com.fasterxml.jackson.core.JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException {
        formatter.setTimeZone(TimeZone.getDefault());
        jsonGenerator.writeString(formatter.format(localDateTime));
    }
}
