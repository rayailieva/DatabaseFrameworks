package softuni.mostwanted.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mostwanted.domain.dtos.json.CarImportDto;
import softuni.mostwanted.domain.entities.Car;
import softuni.mostwanted.repository.CarRepository;
import softuni.mostwanted.util.ValidatorUtil;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
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
