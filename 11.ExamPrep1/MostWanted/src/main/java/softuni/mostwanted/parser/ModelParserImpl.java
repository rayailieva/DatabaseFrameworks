package softuni.mostwanted.parser;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import softuni.mostwanted.parser.interfaces.ModelParser;

public class ModelParserImpl implements ModelParser {

    private final ModelMapper modelMapper;

    public ModelParserImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        return this.modelMapper.map(source, destinationClass);
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        this.modelMapper.addMappings(propertyMap);
        return this.convert(source, destinationClass);
    }
}
