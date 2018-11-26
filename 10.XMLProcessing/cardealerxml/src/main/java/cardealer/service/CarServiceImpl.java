package cardealer.service;

import org.springframework.stereotype.Service;

import cardealer.domain.dtos.*;
import cardealer.domain.entities.Car;
import cardealer.domain.entities.Part;
import cardealer.repository.CarRepository;
import cardealer.repository.PartRepository;
import cardealer.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importCars(CarImportRootDto carImportRootDto) {
        for (CarImportDto carImportDto : carImportRootDto.getCarImportDtos()) {
            if (!this.validationUtil.isValid(carImportDto)) {
                System.out.println("Something went wrong.");

                continue;
            }

            Car carEntity = this.modelMapper.map(carImportDto, Car.class);
            carEntity.setParts(this.getRandomParts());

            this.carRepository.saveAndFlush(carEntity);
        }
    }

    @Override
    public CarExportRootDto getAllByModel(String model) {
       List<Car> carEntities = this.carRepository.getAllByMakeOrderByModelAscTravelledDistanceDesc(model);

       List<CarExportDto> carExportDtos = new ArrayList<>();

       for(Car car : carEntities){
           CarExportDto carExportDto = this.modelMapper.map(car, CarExportDto.class);
           List<PartExportDto> partExportDtos = new ArrayList<>();

           for(Part part: car.getParts()){
               PartExportDto partExportDto = this.modelMapper.map(part, PartExportDto.class);

               partExportDtos.add(partExportDto);
           }

           PartExportRootDto partExportRootDto = new PartExportRootDto();
           partExportRootDto.setPartExportDtos(partExportDtos);
           carExportDto.setPartExportRootDto(partExportRootDto);

           carExportDtos.add(carExportDto);
       }

       CarExportRootDto carExportRootDto = new CarExportRootDto();
       carExportRootDto.setCarExportDtos(carExportDtos);

       return carExportRootDto;
    }

    @Override
    public CarAndPartsExportRootDto getAllCarsWithParts() {
        List<Car> carEntities = this.carRepository.findAll();

        List<CarAndPartsExportDto> carAndPartsExportDtos = new ArrayList<>();

        for(Car car : carEntities){
            CarAndPartsExportDto carAndPartsExportDto = this.modelMapper.map(car, CarAndPartsExportDto.class);
            List<PartExportDto> partExportDtos = new ArrayList<>();

            for(Part part: car.getParts()){
                PartExportDto partExportDto = this.modelMapper.map(part, PartExportDto.class);

                partExportDtos.add(partExportDto);
            }

            PartExportRootDto partExportRootDto = new PartExportRootDto();
            partExportRootDto.setPartExportDtos(partExportDtos);
            carAndPartsExportDto.setPartExportRootDto(partExportRootDto);

            carAndPartsExportDtos.add(carAndPartsExportDto);
        }

        CarAndPartsExportRootDto carAndPartsExportRootDto = new CarAndPartsExportRootDto();
        carAndPartsExportRootDto.setCarAndPartsExportDtos(carAndPartsExportDtos);

        return carAndPartsExportRootDto;
    }

    @Override
    public List<Car> getAllCars() {
        return this.carRepository.findAll();
    }

    private List<Part> getRandomParts() {
        List<Part> parts = new ArrayList<>();
        Random rnd = new Random();

        List<Part> partEntities = this.partRepository.findAll();

        int length = rnd.nextInt(10) + 10;

        for (int i = 0; i < length; i++) {
            int partIndex = rnd.nextInt((int) (this.partRepository.count() - 1)) + 1;

            parts.add(partEntities.get(partIndex));
        }

        return parts;
    }
}