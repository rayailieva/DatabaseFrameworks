package softuni.mostwanted.service;

import softuni.mostwanted.domain.dtos.binding.xml.RaceImportRootDto;

public interface RaceService {

    void importRaces(RaceImportRootDto raceImportRootDto);
}
