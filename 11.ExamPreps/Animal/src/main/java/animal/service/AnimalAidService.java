package animal.service;

import animal.domain.dtos.json.AnimalAidImportDto;

public interface AnimalAidService {

    void importAnimalAids(AnimalAidImportDto[] animalAidImportDtos);
}
