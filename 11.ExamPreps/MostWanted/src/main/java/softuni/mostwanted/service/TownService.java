package softuni.mostwanted.service;

import softuni.mostwanted.domain.dtos.json.TownImportDto;

public interface TownService {

    void importTowns(TownImportDto[] townImportDtos);
}
