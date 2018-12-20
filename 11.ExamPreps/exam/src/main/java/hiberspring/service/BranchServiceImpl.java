package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.BranchImportDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BranchServiceImpl implements BranchService {

    private static final String BRANCHES_FILE_CONTENT =
            Constants.PATH_TO_FILES + "branches.json";

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final BranchRepository branchRepository;
    private final TownRepository townRepository;

    @Autowired
    public BranchServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, Gson gson, BranchRepository branchRepository, TownRepository townRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return this.fileUtil.readFile(BRANCHES_FILE_CONTENT);
    }

    @Override
    public String importBranches(String branchesFileContent) {
        StringBuilder importResult = new StringBuilder();

        BranchImportDto[] branchImportDtos = this.gson.fromJson(branchesFileContent, BranchImportDto[].class);

        for(BranchImportDto branchImportDto : branchImportDtos){

            Branch branch = this.branchRepository
                    .findByName(branchImportDto.getName())
                    .orElse(null);

            Town town = this.townRepository
                    .findByName(branchImportDto.getTown())
                    .orElse(null);

            if(!this.validationUtil.isValid(branchImportDto) || town == null || branch != null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            branch = this.modelMapper.map(branchImportDto, Branch.class);
            branch.setTown(town);
            this.branchRepository.saveAndFlush(branch);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    branch.getClass().getSimpleName(), branch.getName()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }
}
