package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.DistrictImportDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final static String DISTRICTS_FILE_CONTENT =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\MostWanted\\src\\main\\resources\\files\\districts.json";

    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, TownRepository townRepository, FileUtil fileUtil, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() > 0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        return this.fileUtil.readFile(DISTRICTS_FILE_CONTENT);
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        StringBuilder importResult = new StringBuilder();

        DistrictImportDto[] districtImportDtos =
                this.gson.fromJson(districtsFileContent, DistrictImportDto[].class);

        for(DistrictImportDto districtImportDto : districtImportDtos){
            District districtEntity = this.districtRepository.findByName(districtImportDto.getName()).orElse(null);

            if(districtEntity != null){
                importResult.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Town townEntity = this.townRepository.findByName(districtImportDto.getTownName()).orElse(null);
            if(!this.validationUtil.isValid(districtImportDto) || townEntity == null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            districtEntity = this.modelMapper.map(districtImportDto, District.class);
            districtEntity.setTown(townEntity);
            this.districtRepository.saveAndFlush(districtEntity);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, districtEntity.getClass().getSimpleName(), districtEntity.getName()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }
}
