package ruk.config;

import ruk.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.adapters.XmlAdapter;
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
                .setDateFormat("yyyy-MM-dd")
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
}
