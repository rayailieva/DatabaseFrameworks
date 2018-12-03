package softuni.mostwanted.web.controllers;

import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import softuni.mostwanted.domain.dtos.json.CarImportDto;
import softuni.mostwanted.domain.dtos.json.DistrictImportDto;
import softuni.mostwanted.domain.dtos.json.RacerImportDto;
import softuni.mostwanted.domain.dtos.json.TownImportDto;
import softuni.mostwanted.domain.dtos.xml.RaceEntryImportRootDto;
import softuni.mostwanted.domain.dtos.xml.RaceImportRootDto;
import softuni.mostwanted.service.*;
import softuni.mostwanted.util.FileIOUtil;
import softuni.mostwanted.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class AppController implements CommandLineRunner {

    private final static String TOWNS_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\MostWanted\\src\\main\\resources\\files\\json\\input\\towns.json";
    private final static String DISTRICTS_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\MostWanted\\src\\main\\resources\\files\\json\\input\\districts.json";
    private final static String RACERS_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\MostWanted\\src\\main\\resources\\files\\json\\input\\racers.json";
    private final static String CARS_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\MostWanted\\src\\main\\resources\\files\\json\\input\\cars.json";
    private final static String RACE_ENTRIES_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\MostWanted\\src\\main\\resources\\files\\xml\\input\\race-entries.xml";
    private final static String RACES_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\MostWanted\\src\\main\\resources\\files\\xml\\input\\races.xml";


    private final TownService townService;
    private final DistrictService districtService;
    private final RacerService racerService;
    private final CarService carService;
    private final RaceEntryService raceEntryService;
    private final RaceService raceService;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final FileIOUtil fileIOUtil;

    public AppController(TownService townService, DistrictService districtService, RacerService racerService, CarService carService, RaceEntryService raceEntryService, RaceService raceService, Gson gson, XmlParser xmlParser, FileIOUtil fileIOUtil) {
        this.townService = townService;
        this.districtService = districtService;
        this.racerService = racerService;
        this.carService = carService;
        this.raceEntryService = raceEntryService;
        this.raceService = raceService;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.fileIOUtil = fileIOUtil;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.importTowns();
        //this.importDistricts();
        //this.importRacers();
        //this.importCars();
        //this.importRaceEntries();
        // this.importRaces();
    }

    private void importTowns() throws IOException {
        String townsFileContent = this.fileIOUtil.readFile(TOWNS_FILE_PATH);

        TownImportDto[] townImportDtos =
                this.gson.fromJson(townsFileContent, TownImportDto[].class);
        this.townService.importTowns(townImportDtos);
    }

    private void importDistricts() throws IOException {
        String districtsFileContent = this.fileIOUtil.readFile(DISTRICTS_FILE_PATH);

        DistrictImportDto[] districtImportDtos =
                this.gson.fromJson(districtsFileContent, DistrictImportDto[].class);
        this.districtService.importDistricts(districtImportDtos);
    }

    private void importRacers() throws IOException {
        String racersFileContent = this.fileIOUtil.readFile(RACERS_FILE_PATH);

        RacerImportDto[] racerImportDtos =
                this.gson.fromJson(racersFileContent, RacerImportDto[].class);
        this.racerService.importRacers(racerImportDtos);
    }

    private void importCars() throws IOException {
        String carsFileContent = this.fileIOUtil.readFile(CARS_FILE_PATH);

        CarImportDto[] carImportDtos =
                this.gson.fromJson(carsFileContent, CarImportDto[].class);
        this.carService.importCars(carImportDtos);
    }

    private void importRaceEntries() throws JAXBException, FileNotFoundException {
        RaceEntryImportRootDto raceEntryImportRootDto =
                this.xmlParser.parseXml(RaceEntryImportRootDto.class, RACE_ENTRIES_FILE_PATH);

        this.raceEntryService.importRaceEntries(raceEntryImportRootDto);
    }

    private void importRaces() throws JAXBException, FileNotFoundException {
        RaceImportRootDto raceImportRootDto =
                this.xmlParser.parseXml(RaceImportRootDto.class, RACES_FILE_PATH);

        this.raceService.importRaces(raceImportRootDto);
    }

}
