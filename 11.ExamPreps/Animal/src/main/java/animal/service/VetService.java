package animal.service;

import animal.domain.dtos.xml.VetImportRootDto;

public interface VetService {

    void importVets(VetImportRootDto vetImportRootDto);
}
