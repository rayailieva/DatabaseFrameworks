package ruk.service;

import ruk.domain.dtos.BranchImportDto;

public interface BranchService {

    void importBranches(BranchImportDto[] branchImportDtos);
}
