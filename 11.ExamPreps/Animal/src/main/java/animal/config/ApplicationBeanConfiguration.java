package animal.config;

import animal.util.*;
import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public FileIOUtil fileIOUtil() {
        return new FileIOUtilImpl();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .registerTypeAdapter(Date.class, new DateSerializer())
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ValidatorUtil validatorUtil() {
        return new ValidatorUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public XmlParser xmlParser() {
        return new XmlParserImpl();
    }

    @Component
    public static class DateTimeAdapter extends XmlAdapter<String, Date> {
        private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        @Override
        public Date unmarshal(String v) throws Exception {
            return this.format.parse(v);
        }

        @Override
        public String marshal(Date v) {
            return this.format.format(v);
        }
    }

    private static class DateSerializer implements JsonSerializer<Date> {
        @Override
        public JsonElement serialize(final Date date, final Type typeOfSrc, final JsonSerializationContext context) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            return new JsonPrimitive(sdf.format(date));
        }
    }

    private static class DateDeserializer implements JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = null;
            try {
                date = sdf.parse(json.getAsJsonPrimitive().getAsString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
    }
}
