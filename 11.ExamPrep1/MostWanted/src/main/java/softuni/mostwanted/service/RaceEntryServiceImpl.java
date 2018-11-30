package softuni.mostwanted.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mostwanted.domain.dtos.binding.xml.RaceEntriesImportDto;
import softuni.mostwanted.domain.dtos.binding.xml.RaceEntriesImportRootDto;
import softuni.mostwanted.domain.entities.RaceEntry;
import softuni.mostwanted.repository.RaceEntriesRepository;
import softuni.mostwanted.util.ValidatorUtil;
import softuni.mostwanted.util.ValidatorUtilImpl;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {

    private final RaceEntriesRepository raceEntriesRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public RaceEntryServiceImpl(RaceEntriesRepository raceEntriesRepository) {
        this.raceEntriesRepository = raceEntriesRepository;
        this.modelMapper = new ModelMapper();
        this.validatorUtil = new ValidatorUtilImpl();
    }

    @Override
    public void importRaceEntries(RaceEntriesImportRootDto raceEntriesImportRootDto) {
       for(RaceEntriesImportDto raceEntriesImportDto : raceEntriesImportRootDto.getRaceEntriesImportDtos()){
           if(!this.validatorUtil.isValid(raceEntriesImportDto)){
               System.out.println("Something went wrong!");
               continue;
           }

           RaceEntry entity = this.modelMapper.map(raceEntriesImportDto, RaceEntry.class);
           this.raceEntriesRepository.saveAndFlush(entity);
       }
    }
}
