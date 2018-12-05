package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.RacerImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RacerServiceImpl implements RacerService {

    private final static String RACERS_FILE_CONTENT =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\MostWanted\\src\\main\\resources\\files\\racers.json";

    private final RacerRepository racerRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, FileUtil fileUtil, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() > 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        return this.fileUtil.readFile(RACERS_FILE_CONTENT);
    }

    @Override
    public String importRacers(String racersFileContent) {
        StringBuilder importResult = new StringBuilder();

        RacerImportDto[] racerImportDtos =
                this.gson.fromJson(racersFileContent, RacerImportDto[].class);

        for(RacerImportDto racerImportDto : racerImportDtos){
            Racer racerEntity = this.racerRepository.findByName(racerImportDto.getName()).orElse(null);

            if(racerEntity != null){
                importResult.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Town townEntity = this.townRepository.findByName(racerImportDto.getTownName()).orElse(null);
            if(!this.validationUtil.isValid(racerImportDto) || townEntity == null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            racerEntity = this.modelMapper.map(racerImportDto, Racer.class);
            racerEntity.setHomeTown(townEntity);
            this.racerRepository.saveAndFlush(racerEntity);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, racerEntity.getClass().getSimpleName(), racerEntity.getName()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }

    @Override
    public String exportRacingCars() {
        StringBuilder exportResult = new StringBuilder();

        List<Racer> racers =
                this.racerRepository.exportRacersWithCars();

        racers.stream().forEach(racer -> {
            exportResult.append(String.format("Name: %s", racer.getName())).append(System.lineSeparator());
            if(racer.getAge() != null){
                exportResult.append(String.format("Age: %d", racer.getAge())).append(System.lineSeparator());
            }

            exportResult.append("Cars: ").append(System.lineSeparator());

            racer.getCars().stream().forEach(car -> {
                exportResult.append(String.format("%s %s %d", car.getBrand(), car.getModel(), car.getYearOfProduction()))
                        .append(System.lineSeparator());
            });

            exportResult.append(System.lineSeparator());
        });

        return exportResult.toString().trim();
    }
}
