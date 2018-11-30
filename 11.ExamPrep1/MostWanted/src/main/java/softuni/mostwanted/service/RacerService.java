package softuni.mostwanted.service;

import softuni.mostwanted.domain.dtos.binding.json.RacerImportDto;

public interface RacerService {

    void importRacers(RacerImportDto[] racerImportDtos);
}
