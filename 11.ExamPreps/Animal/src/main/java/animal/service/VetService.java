package animal.service;

import animal.domain.dtos.vets.VetImportRootDto;

public interface VetService {

    void importVets(VetImportRootDto vetImportRootDto);
}
