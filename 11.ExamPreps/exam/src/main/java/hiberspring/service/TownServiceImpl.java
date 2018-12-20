package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.TownImportDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWNS_FILE_CONTENT =
            Constants.PATH_TO_FILES + "towns.json";

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, Gson gson, TownRepository townRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.townRepository = townRepository;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile(TOWNS_FILE_CONTENT);
    }

    @Override
    public String importTowns(String townsFileContent) {
        StringBuilder importResult = new StringBuilder();

        TownImportDto[] townImportDtos = this.gson.fromJson(townsFileContent, TownImportDto[].class);

        for(TownImportDto townImportDto : townImportDtos){

            Town town = this.townRepository
                    .findByName(townImportDto.getName())
                    .orElse(null);

            if(!this.validationUtil.isValid(townImportDto) || town != null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            town = this.modelMapper.map(townImportDto, Town.class);
            this.townRepository.saveAndFlush(town);

            importResult
                    .append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                     town.getClass().getSimpleName(),town.getName()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }
}
