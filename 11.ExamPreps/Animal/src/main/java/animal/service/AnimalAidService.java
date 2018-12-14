package animal.service;

import animal.domain.dtos.AnimalAidImportDto;

public interface AnimalAidService {

    void importAnimalAid(AnimalAidImportDto[] animalAidImportDtos);
}
