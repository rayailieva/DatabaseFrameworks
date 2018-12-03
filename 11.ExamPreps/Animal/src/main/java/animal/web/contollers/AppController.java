package animal.web.contollers;

import animal.domain.dtos.json.AnimalAidImportDto;
import animal.domain.dtos.xml.VetImportRootDto;
import animal.service.AnimalAidService;
import animal.service.VetService;
import animal.util.FileIOUtil;
import animal.util.XmlParser;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class AppController implements CommandLineRunner {

    private final static String ANIMAL_AIDS_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\Animal\\src\\main\\resources\\files\\json\\animal_aids.json";
    private final static String VETS_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\Animal\\src\\main\\resources\\files\\xml\\vets.xml";
    private final static String ANIMALS_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\Animal\\src\\main\\resources\\files\\json\\animals.json";

    private final FileIOUtil fileIOUtil;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final AnimalAidService animalAidService;
    private final VetService vetService;

    public AppController(FileIOUtil fileIOUtil, Gson gson, XmlParser xmlParser, AnimalAidService animalAidService, VetService vetService) {
        this.fileIOUtil = fileIOUtil;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.animalAidService = animalAidService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.importAnimalAids();
        //this.importVets();
    }

    private void importAnimalAids() throws IOException {
        String animalAidsFileContent = this.fileIOUtil.readFile(ANIMAL_AIDS_FILE_PATH);

        AnimalAidImportDto[] animalAidImportDtos =
                this.gson.fromJson(animalAidsFileContent, AnimalAidImportDto[].class);
        this.animalAidService.importAnimalAids(animalAidImportDtos);
    }

    private void importVets() throws JAXBException, FileNotFoundException {
        VetImportRootDto vetImportRootDto =
                this.xmlParser.parseXml(VetImportRootDto.class, VETS_FILE_PATH);

        this.vetService.importVets(vetImportRootDto);
    }
}
