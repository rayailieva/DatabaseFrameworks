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
public class EmployeeServiceImpl implements EmployeeService{

    private static final String EMPLOYEES_FILE_CONTENT =
            System.getProperty("user.dir") + "/src/main/resources/files/employees.json";

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    @Autowired
    public EmployeeServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, Gson gson, EmployeeRepository employeeRepository, PositionRepository positionRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
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

        EmployeeImportDto[] employeeImportDtos = this.gson.fromJson(employees, EmployeeImportDto[].class);

        for(EmployeeImportDto employeeImportDto : employeeImportDtos){

            if(!this.validationUtil.isValid(employeeImportDto)){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }


            Position position = this.positionRepository
                    .findByName(employeeImportDto.getPosition())
                    .orElse(null);

            if(position == null){
                position = new Position();
                position.setName(employeeImportDto.getPosition());
                this.positionRepository.saveAndFlush(position);
            }

            Employee employee = this.modelMapper.map(employeeImportDto, Employee.class);
            employee.setPosition(position);
            this.employeeRepository.saveAndFlush(employee);

            importResult.append(String.format("Record %s successfully imported.", employee.getName()))
                    .append(System.lineSeparator());

        }

        return importResult.toString().trim();
    }
}
