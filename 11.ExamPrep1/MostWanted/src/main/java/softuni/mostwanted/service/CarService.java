package softuni.mostwanted.service;

import softuni.mostwanted.domain.dtos.binding.json.CarImportDto;

public interface CarService {

    void importCars(CarImportDto[] carImportDtos);
}
