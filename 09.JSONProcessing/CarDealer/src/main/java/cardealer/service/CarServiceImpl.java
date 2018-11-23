package cardealer.service;

import cardealer.domain.dtos.CarViewDto;
import cardealer.domain.dtos.CarsSeedDto;
import cardealer.domain.entities.Car;
import cardealer.repository.CarRepository;
import cardealer.util.ValidatorUtil;
import cardealer.util.ValidatorUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PartsService partsService;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    public CarServiceImpl(CarRepository carRepository, PartsService partsService) {
        this.carRepository = carRepository;
        this.partsService = partsService;
        this.modelMapper = new ModelMapper();
        this.validatorUtil = new ValidatorUtilImpl();
    }

    @Override
    public void seedCars(CarsSeedDto[] carsSeedDtos) {
        for(CarsSeedDto carsSeedDto : carsSeedDtos){
            if(!this.validatorUtil.isValid(carsSeedDto)){
                this.validatorUtil.violations(carsSeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));

                continue;
            }

            Car entity = this.modelMapper.map(carsSeedDto, Car.class);
            this.carRepository.saveAndFlush(entity);
        }
    }

    @Override
    public List<Car> getAllCars() {
        return this.carRepository.findAll();
    }

    @Override
    public List<CarViewDto> getCarsByMake(final String make) {
        return this.carRepository
                .findByMakeOrderByModelAsc(make)
                .stream()
                .map(car -> this.modelMapper.map(car, CarViewDto.class))
                .collect(Collectors.toList());
    }

}
