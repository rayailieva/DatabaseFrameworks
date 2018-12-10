package ruk.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruk.domain.dtos.BranchImportDto;
import ruk.domain.entities.Branch;
import ruk.repository.BranchRepository;
import ruk.util.ValidatorUtil;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.branchRepository = branchRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importBranches(BranchImportDto[] branchImportDtos) {
        for(BranchImportDto branchImportDto : branchImportDtos){
            if(!this.validatorUtil.isValid(branchImportDto)){
                this.validatorUtil.violations(branchImportDto)
                        .forEach(System.out::println);
                continue;
            }

            Branch branch = this.modelMapper.map(branchImportDto, Branch.class);
            this.branchRepository.saveAndFlush(branch);
        }
    }
}
