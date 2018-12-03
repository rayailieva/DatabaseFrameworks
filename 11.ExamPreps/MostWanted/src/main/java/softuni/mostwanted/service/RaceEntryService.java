package softuni.mostwanted.service;

import softuni.mostwanted.domain.dtos.xml.RaceEntryImportRootDto;

public interface RaceEntryService {

    void importRaceEntries(RaceEntryImportRootDto raceEntryImportRootDto);
}
