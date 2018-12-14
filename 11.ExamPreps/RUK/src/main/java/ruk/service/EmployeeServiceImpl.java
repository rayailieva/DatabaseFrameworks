package ruk.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruk.domain.dtos.EmployeeImportDto;
import ruk.domain.entities.Branch;
import ruk.domain.entities.Employee;
import ruk.repository.BranchRepository;
import ruk.repository.EmployeeRepository;
import ruk.util.ValidatorUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public String importEmployees(EmployeeImportDto[] employeeImportDtos) {
        StringBuilder importResult = new StringBuilder();

        for(EmployeeImportDto employeeImportDto : employeeImportDtos){
            if(!this.validatorUtil.isValid(employeeImportDto)){
                importResult.append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
                break;
            }

            Branch branch = this.branchRepository.findOneByName(employeeImportDto.getBranch_name());

            String[] names = employeeImportDto.getFull_name().split("\\s+");

            Employee employee = this.modelMapper.map(employeeImportDto, Employee.class);
            employee.setFirstName(names[0]);
            employee.setLastName(names[1]);
            employee.setBranch(branch);
            employee.setStartedOn(employeeImportDto.getStarted_on());

            this.employeeRepository.saveAndFlush(employee);

            importResult.append(String.format("Successfully imported Employee - %s %s",
                    employee.getFirstName(), employee.getLastName()))
                    .append(System.lineSeparator());

        }

        return importResult.toString().trim();
    }
}
