package softuni.mostwanted.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mostwanted.domain.dtos.xml.RaceImportDto;
import softuni.mostwanted.domain.dtos.xml.RaceImportRootDto;
import softuni.mostwanted.domain.entities.Race;
import softuni.mostwanted.repository.RaceRepository;
import softuni.mostwanted.util.ValidatorUtil;

@Service
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.raceRepository = raceRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importRaces(RaceImportRootDto raceImportRootDto) {
        for(RaceImportDto raceImportDto : raceImportRootDto.getRaceImportDtos()){
            if(!this.validatorUtil.isValid(raceImportDto)){
               this.validatorUtil.violations(raceImportDto)
                       .forEach(System.out::println);
                continue;
            }

            Race entity = this.modelMapper.map(raceImportDto, Race.class);
            this.raceRepository.saveAndFlush(entity);
        }
    }
}
