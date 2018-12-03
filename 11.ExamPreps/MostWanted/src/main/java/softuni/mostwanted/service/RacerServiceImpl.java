package softuni.mostwanted.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mostwanted.domain.dtos.json.RacerImportDto;
import softuni.mostwanted.domain.entities.Race;
import softuni.mostwanted.domain.entities.Racer;
import softuni.mostwanted.repository.RacerRepository;
import softuni.mostwanted.util.ValidatorUtil;

@Service
public class RacerServiceImpl implements RacerService {

    private final RacerRepository racerRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.racerRepository = racerRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importRacers(RacerImportDto[] racerImportDtos) {
        for(RacerImportDto racerImportDto : racerImportDtos){
            if(!this.validatorUtil.isValid(racerImportDto)){
                this.validatorUtil.violations(racerImportDto)
                        .forEach(System.out::println);
                continue;
            }

            Racer entity = this.modelMapper.map(racerImportDto, Racer.class);
            this.racerRepository.saveAndFlush(entity);
        }

    }
}
