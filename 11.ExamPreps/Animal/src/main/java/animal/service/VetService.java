package animal.service;

import animal.domain.dtos.VetImportRootDto;

public interface VetService {

    void importVets(VetImportRootDto vetImportRootDto);
}
