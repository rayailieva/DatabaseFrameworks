package softuni.mostwanted.service;

import softuni.mostwanted.domain.dtos.xml.RaceImportRootDto;

public interface RaceService {

    void importRaces(RaceImportRootDto raceImportRootDto);
}
