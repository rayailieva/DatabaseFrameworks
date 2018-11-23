package cardealer.service;

import cardealer.domain.dtos.binding.PartsSeedDto;
import cardealer.domain.entities.Part;

import java.util.List;

public interface PartsService {

    void seedParts(PartsSeedDto[] partsSeedDtos);

    List<Part> getAllParts();
}
