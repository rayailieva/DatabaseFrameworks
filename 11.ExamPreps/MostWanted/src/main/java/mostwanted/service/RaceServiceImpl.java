package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.EntryImportDto;
import mostwanted.domain.dtos.RaceEntryImportDto;
import mostwanted.domain.dtos.RaceImportDto;
import mostwanted.domain.dtos.RaceImportRootDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {

    private final static String RACES_FILE_CONTENT =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\MostWanted\\src\\main\\resources\\files\\races.xml";

    private final RaceRepository raceRepository;
    private final DistrictRepository districtRepository;
    private final RaceEntryRepository raceEntryRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, DistrictRepository districtRepository, RaceEntryRepository raceEntryRepository, FileUtil fileUtil, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.raceRepository = raceRepository;
        this.districtRepository = districtRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public Boolean racesAreImported() {
        return this.raceRepository.count() > 0;
    }

    @Override
    public String readRacesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACES_FILE_CONTENT);
    }

    @Override
    public String importRaces() throws JAXBException, FileNotFoundException {
        StringBuilder resultImport = new StringBuilder();

        RaceImportRootDto raceImportRootDto =
                this.xmlParser.parseXml(RaceImportRootDto.class, RACES_FILE_CONTENT);

        for (RaceImportDto raceImportDto : raceImportRootDto.getRaceImportDtos()) {
            District districtEntity = this.districtRepository.findByName(raceImportDto.getDistrict()).orElse(null);
            if (!this.validationUtil.isValid(raceImportDto) || districtEntity == null) {
                resultImport.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            Race raceEntity = this.modelMapper.map(raceImportDto, Race.class);
            raceEntity.setDistrict(districtEntity);

            List<RaceEntry> entries = new ArrayList<>();

            for(EntryImportDto entryImportDto : raceImportDto.getEntryImportRootDto().getEntryImportDtos()){
                RaceEntry raceEntryEntity = this.raceEntryRepository.findById(entryImportDto.getId()).orElse(null);

                if(raceEntryEntity == null){
                    resultImport.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                    continue;
                }

                raceEntryEntity.setRace(raceEntity);
                entries.add(raceEntryEntity);
            }

            this.raceRepository.saveAndFlush(raceEntity);
            resultImport.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    raceEntity.getClass().getSimpleName(),
                    raceEntity.getId()))
                     .append(System.lineSeparator());
        }

        return resultImport.toString().trim();
    }
}
