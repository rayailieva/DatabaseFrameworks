package softuni.mostwanted.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.mostwanted.domain.dtos.binding.xml.RaceImportDto;
import softuni.mostwanted.domain.dtos.binding.xml.RaceImportRootDto;
import softuni.mostwanted.domain.entities.Race;
import softuni.mostwanted.repository.RaceRepository;
import softuni.mostwanted.util.ValidatorUtil;
import softuni.mostwanted.util.ValidatorUtilImpl;

@Service
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public RaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
        this.validatorUtil = new ValidatorUtilImpl();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void importRaces(RaceImportRootDto raceImportRootDto) {
        for(RaceImportDto raceImportDto : raceImportRootDto.getRaceImportDtos()){
            if(!this.validatorUtil.isValid(raceImportDto)){
                System.out.println("Something went wrong!");
                continue;
            }

            Race entity = this.modelMapper.map(raceImportDto, Race.class);
            this.raceRepository.saveAndFlush(entity);
        }
    }
}
