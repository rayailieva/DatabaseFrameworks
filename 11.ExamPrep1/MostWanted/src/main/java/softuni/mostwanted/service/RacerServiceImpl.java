package softuni.mostwanted.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.mostwanted.domain.dtos.binding.json.RacerImportDto;
import softuni.mostwanted.domain.entities.Racer;
import softuni.mostwanted.repository.RacerRepository;
import softuni.mostwanted.util.ValidatorUtil;
import softuni.mostwanted.util.ValidatorUtilImpl;

@Service
public class RacerServiceImpl implements RacerService {

    private final RacerRepository racerRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    public RacerServiceImpl(RacerRepository racerRepository) {
        this.racerRepository = racerRepository;
        this.modelMapper = new ModelMapper();
        this.validatorUtil = new ValidatorUtilImpl();
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
