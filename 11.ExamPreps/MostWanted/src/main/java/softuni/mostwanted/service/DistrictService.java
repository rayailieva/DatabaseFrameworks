package softuni.mostwanted.service;

import softuni.mostwanted.domain.dtos.json.DistrictImportDto;

public interface DistrictService {

    void importDistricts(DistrictImportDto[] districtImportDtos);
}
