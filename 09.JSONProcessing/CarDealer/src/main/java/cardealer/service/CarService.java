package cardealer.service;

import cardealer.domain.dtos.view.CarViewDto;
import cardealer.domain.dtos.binding.CarsSeedDto;
import cardealer.domain.entities.Car;

import java.util.List;

public interface CarService {

    void seedCars(CarsSeedDto[] carsSeedDtos);

    List<Car> getAllCars();


    List<CarViewDto> getCarsByMake(String make);
}
