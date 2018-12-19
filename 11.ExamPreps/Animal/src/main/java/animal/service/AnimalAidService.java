package animal.service;

import animal.domain.dtos.AnimalAidImportDto;

public interface AnimalAidService {

    void importAnimalAids(AnimalAidImportDto[] animalAidImportDtos);
}
