package animal.service;

import animal.domain.dtos.procedures.ProcedureImportRootDto;

public interface ProcedureService {

    void importProcedures(ProcedureImportRootDto procedureImportRootDto);
}
