package ruk.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruk.domain.dtos.BranchImportDto;
import ruk.domain.entities.Branch;
import ruk.repository.BranchRepository;
import ruk.util.ValidatorUtil;

@Service
public class BranchServiceImpl implements BranchService{

    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importBranches(BranchImportDto[] branchImportDtos) {

        for(BranchImportDto branchImportDto : branchImportDtos){
            if(!this.validatorUtil.isValid(branchImportDto)){
                System.out.println("Error: Incorrect Data!");
                continue;
            }

            Branch branch = this.modelMapper.map(branchImportDto, Branch.class);

            this.branchRepository.saveAndFlush(branch);
        }

    }
}
