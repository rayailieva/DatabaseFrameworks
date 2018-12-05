package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.RaceEntryImportDto;
import mostwanted.domain.dtos.RaceEntryImportRootDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {

    private final static String RACE_ENTRIES_FILE_CONTENT =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\MostWanted\\src\\main\\resources\\files\\race-entries.xml";

    private final RaceEntryRepository raceEntryRepository;
    private final RacerRepository racerRepository;
    private final CarRepository carRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, RacerRepository racerRepository, CarRepository carRepository, FileUtil fileUtil, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.raceEntryRepository = raceEntryRepository;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() > 0;
    }

    @Override
    public String readRaceEntriesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACE_ENTRIES_FILE_CONTENT);
    }

    @Override
    public String importRaceEntries() throws JAXBException, FileNotFoundException {
        StringBuilder importResult = new StringBuilder();

        RaceEntryImportRootDto raceEntryImportRootDto =
                this.xmlParser.parseXml(RaceEntryImportRootDto.class, RACE_ENTRIES_FILE_CONTENT);

        for(RaceEntryImportDto raceEntryImportDto : raceEntryImportRootDto.getRaceEntryImportDtos()){
            Racer racerEntity =
                    this.racerRepository.findByName(raceEntryImportDto.getRacerName()).orElse(null);
            Car carEntity =
                    this.carRepository.findById(raceEntryImportDto.getCarId()).orElse(null);

            if(carEntity == null || racerEntity == null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            RaceEntry raceEntryEntity = this.modelMapper.map(raceEntryImportDto, RaceEntry.class);
            raceEntryEntity.setRacer(racerEntity);
            raceEntryEntity.setCar(carEntity);
            raceEntryEntity.setRace(null);
            this.raceEntryRepository.saveAndFlush(raceEntryEntity);

            importResult.append(String
                    .format(Constants.SUCCESSFUL_IMPORT_MESSAGE, raceEntryEntity.getClass().getSimpleName(), raceEntryEntity.getId()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }
}
