package cardealer.service;

import cardealer.domain.dtos.CarAndPartsExportRootDto;
import cardealer.domain.dtos.CarExportRootDto;
import cardealer.domain.dtos.CarImportRootDto;
import cardealer.domain.entities.Car;

import java.util.List;

public interface CarService {

    void importCars(CarImportRootDto carImportRootDto);

    CarExportRootDto getAllByModel(String model);

    CarAndPartsExportRootDto getAllCarsWithParts();

    List<Car> getAllCars();
}
