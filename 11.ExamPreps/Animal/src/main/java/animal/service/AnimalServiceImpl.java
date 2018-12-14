package animal.service;

import animal.domain.dtos.AnimalImportDto;
import animal.domain.dtos.PassportImportDto;
import animal.domain.entities.Animal;
import animal.domain.entities.Passport;
import animal.repository.AnimalRepository;
import animal.repository.PassportRepository;
import animal.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final PassportRepository passportRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository, PassportRepository passportRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.animalRepository = animalRepository;
        this.passportRepository = passportRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importAnimals(AnimalImportDto[] animalImportDtos) {

        for(AnimalImportDto animalImportDto : animalImportDtos) {
            if (!this.validatorUtil.isValid(animalImportDto)) {
                System.out.println("invalid animal!");
                break;
            }

            PassportImportDto passportImportDto = animalImportDto.getPassport();
            if (!this.validatorUtil.isValid(passportImportDto)) {
                System.out.println("invalid passport!");
                continue;
            }

            Passport passport = this.modelMapper.map(passportImportDto, Passport.class);
            Animal animal = this.modelMapper.map(animalImportDto, Animal.class);

           passport.setAnimal(animal);
            this.passportRepository.saveAndFlush(passport);


          animal.setPassport(passport);

          this.animalRepository.saveAndFlush(animal);

        }
    }
}
