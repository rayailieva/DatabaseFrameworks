package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.TownImportDto;
import mostwanted.domain.entities.Town;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TownServiceImpl implements TownService {

    private final static String TOWNS_FILE_CONTENT =
           "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\MostWanted\\src\\main\\resources\\files\\towns.json";

    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ValidationUtil validationUtil, ModelMapper modelMapper, FileUtil fileUtil, Gson gson) {
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
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

        TownImportDto[] townImportDtos =
                this.gson.fromJson(townsFileContent, TownImportDto[].class);

        for(TownImportDto townImportDto : townImportDtos){
            Town townEntity = this.townRepository.findByName(townImportDto.getName()).orElse(null);

            if(townEntity != null){
                importResult.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
               continue;
            }

            if(!this.validationUtil.isValid(townImportDto)){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            townEntity = this.modelMapper.map(townImportDto, Town.class);
            this.townRepository.saveAndFlush(townEntity);
            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, townEntity.getClass().getSimpleName(), townEntity.getName()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }

    @Override
    public String exportRacingTowns() {
        return null;
    }
}
