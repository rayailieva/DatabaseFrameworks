package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.EmployeeCardImportDto;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {

    private static final String EMPLOYEE_CARDS_FILE_CONTENT =
            Constants.PATH_TO_FILES + "employee-cards.json";

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final EmployeeCardRepository employeeCardRepository;

    @Autowired
    public EmployeeCardServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, Gson gson, EmployeeCardRepository employeeCardRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.employeeCardRepository = employeeCardRepository;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEE_CARDS_FILE_CONTENT);
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) {
        StringBuilder importResult = new StringBuilder();

        EmployeeCardImportDto[] employeeCardImportDtos = this.gson.fromJson(employeeCardsFileContent, EmployeeCardImportDto[].class);

        for(EmployeeCardImportDto employeeCardImportDto : employeeCardImportDtos){

            EmployeeCard employeeCard = this.employeeCardRepository
                    .findByNumber(employeeCardImportDto.getNumber())
                    .orElse(null);

            if(!this.validationUtil.isValid(employeeCardImportDto) || employeeCard != null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                    continue;
            }

            employeeCard = this.modelMapper.map(employeeCardImportDto, EmployeeCard.class);
            this.employeeCardRepository.saveAndFlush(employeeCard);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    employeeCard.getClass().getSimpleName(), employeeCard.getNumber()))
                    .append(System.lineSeparator());

        }

        return importResult.toString().trim();
    }
}
