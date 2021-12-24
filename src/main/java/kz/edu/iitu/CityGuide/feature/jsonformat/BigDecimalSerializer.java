package kz.edu.iitu.CityGuide.feature.jsonformat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@JsonComponent
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(bigDecimal.setScale(2, RoundingMode.HALF_UP).toString());
    }
}
