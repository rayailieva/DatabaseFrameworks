package softuni.mostwanted.service;

import softuni.mostwanted.domain.dtos.json.CarImportDto;

public interface CarService {

    void importCars(CarImportDto[] carImportDtos);
}
