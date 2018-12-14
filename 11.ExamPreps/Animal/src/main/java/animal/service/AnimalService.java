package animal.service;

import animal.domain.dtos.AnimalImportDto;

public interface AnimalService {

    void importAnimals(AnimalImportDto[] animalImportDtos);
}
