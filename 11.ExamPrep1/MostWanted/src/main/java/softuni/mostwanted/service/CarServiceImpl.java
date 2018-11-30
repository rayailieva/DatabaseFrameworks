package softuni.mostwanted.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.mostwanted.domain.dtos.binding.json.CarImportDto;
import softuni.mostwanted.domain.entities.Car;
import softuni.mostwanted.repository.CarRepository;
import softuni.mostwanted.util.ValidatorUtil;
import softuni.mostwanted.util.ValidatorUtilImpl;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.modelMapper = new ModelMapper();
        this.validatorUtil = new ValidatorUtilImpl();
    }

    @Override
    public void importCars(CarImportDto[] carImportDtos) {
        for(CarImportDto carImportDto : carImportDtos){
            if(!this.validatorUtil.isValid(carImportDto)){
                this.validatorUtil.violations(carImportDto)
                        .forEach(System.out::println);
                continue;
            }

            Car entity = this.modelMapper.map(carImportDto, Car.class);
            this.carRepository.saveAndFlush(entity);
        }
    }
}
