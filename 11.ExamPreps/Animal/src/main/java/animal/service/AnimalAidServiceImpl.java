package animal.service;

import animal.domain.dtos.json.AnimalAidImportDto;
import animal.domain.entities.AnimalAid;
import animal.repository.AnimalAidRepository;
import animal.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalAidServiceImpl implements AnimalAidService {

    private final AnimalAidRepository animalAidRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public AnimalAidServiceImpl(AnimalAidRepository animalAidRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.animalAidRepository = animalAidRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importAnimalAids(AnimalAidImportDto[] animalAidImportDtos) {
        for(AnimalAidImportDto animalAidImportDto : animalAidImportDtos){
            if(!this.validatorUtil.isValid(animalAidImportDto)){
                this.validatorUtil.violations(animalAidImportDto)
                        .forEach(System.out::println);
                continue;
            }

            AnimalAid entity = this.modelMapper.map(animalAidImportDto, AnimalAid.class);
            this.animalAidRepository.saveAndFlush(entity);
        }
    }
}