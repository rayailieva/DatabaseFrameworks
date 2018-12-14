package alararestaurant.service;

import alararestaurant.constants.Constants;
import alararestaurant.domain.dtos.EmployeeImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEES_FILE_CONTENT =
    "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\AlaraRestaurant\\src\\main\\resources\\files\\employees.json";

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, Gson gson) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEES_FILE_CONTENT);
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder importResult = new StringBuilder();

        EmployeeImportDto[] employeeImportDtos =
                this.gson.fromJson(employees, EmployeeImportDto[].class);

        for(EmployeeImportDto employeeImportDto : employeeImportDtos){

            if(!this.validationUtil.isValid(employeeImportDto)){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Position positionEntity = this.positionRepository.findOneByName(employeeImportDto.getPosition()).orElse(null);

            if(positionEntity == null){
                positionEntity = new Position();
                positionEntity.setName(employeeImportDto.getPosition());
                this.positionRepository.saveAndFlush(positionEntity);
            }

            Employee employeeEntity = this.modelMapper.map(employeeImportDto, Employee.class);
            employeeEntity.setPosition(positionEntity);
            this.employeeRepository.saveAndFlush(employeeEntity);

            importResult.append(String.format("Record %s successfully imported", employeeEntity.getName()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }
}
