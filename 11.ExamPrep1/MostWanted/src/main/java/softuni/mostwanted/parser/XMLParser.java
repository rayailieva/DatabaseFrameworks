package softuni.mostwanted.parser;

import softuni.mostwanted.parser.interfaces.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class XMLParser implements Parser {
    @Override
    @SuppressWarnings("unchecked")
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(fileContent));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> String write(T object) throws IOException, JAXBException {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());

            final Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.displayName());

            final StringWriter writer = new StringWriter();

            marshaller.marshal(object, writer);

            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
