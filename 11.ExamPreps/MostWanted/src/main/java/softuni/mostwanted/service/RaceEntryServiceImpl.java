package softuni.mostwanted.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mostwanted.domain.dtos.xml.RaceEntryImportDto;
import softuni.mostwanted.domain.dtos.xml.RaceEntryImportRootDto;
import softuni.mostwanted.domain.entities.RaceEntry;
import softuni.mostwanted.repository.RaceEntryRepository;
import softuni.mostwanted.util.ValidatorUtil;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {

    private final RaceEntryRepository raceEntryRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.raceEntryRepository = raceEntryRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importRaceEntries(RaceEntryImportRootDto raceEntryImportRootDto) {
        for(RaceEntryImportDto raceEntryImportDto : raceEntryImportRootDto.getRaceEntryImportDtos()){
            if(!this.validatorUtil.isValid(raceEntryImportDto)){
                this.validatorUtil.violations(raceEntryImportDto)
                        .forEach(System.out::println);
                continue;
            }

            RaceEntry entity = this.modelMapper.map(raceEntryImportDto, RaceEntry.class);
            this.raceEntryRepository.saveAndFlush(entity);
        }

    }
}
