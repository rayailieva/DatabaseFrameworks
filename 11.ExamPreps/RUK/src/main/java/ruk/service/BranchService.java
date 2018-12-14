package ruk.service;

import ruk.domain.dtos.BranchImportDto;

public interface BranchService {

    String importBranches(BranchImportDto[] branchImportDtos);
}
