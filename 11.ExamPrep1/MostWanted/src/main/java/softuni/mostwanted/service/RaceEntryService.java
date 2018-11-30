package softuni.mostwanted.service;

import softuni.mostwanted.domain.dtos.binding.xml.RaceEntriesImportRootDto;

public interface RaceEntryService {

    void importRaceEntries(RaceEntriesImportRootDto raceEntriesImportRootDto);
}
