package hiberspring.service;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.employees.EmployeeImportDto;
import hiberspring.domain.dtos.employees.EmployeeImportRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEES_FILE_CONTENT =
            Constants.PATH_TO_FILES + "employees.xml";

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final EmployeeRepository employeeRepository;
    private final EmployeeCardRepository employeeCardRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public EmployeeServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, XmlParser xmlParser, EmployeeRepository employeeRepository, EmployeeCardRepository employeeCardRepository, BranchRepository branchRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.employeeRepository = employeeRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEES_FILE_CONTENT);
    }

    @Override
    public String importEmployees() throws JAXBException, FileNotFoundException {
        StringBuilder importResult = new StringBuilder();

        EmployeeImportRootDto employeeImportRootDto =
                this.xmlParser.parseXml(EmployeeImportRootDto.class, EMPLOYEES_FILE_CONTENT);

        for(EmployeeImportDto employeeImportDto : employeeImportRootDto.getEmployeeImportDtos()){

            EmployeeCard employeeCard = this.employeeCardRepository
                    .findByNumber(employeeImportDto.getCard())
                    .orElse(null);

            Employee employee = new Employee();

            if(employeeCard != null) {
               employee = this.employeeRepository
                        .findByCard(employeeCard)
                        .orElse(null);
            }

            Branch branch = this.branchRepository
                    .findByName(employeeImportDto.getBranch())
                    .orElse(null);

            if(!this.validationUtil.isValid(employeeImportDto) ||
                    employeeCard == null || branch == null || employee != null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            employee = this.modelMapper.map(employeeImportDto, Employee.class);
            employee.setCard(employeeCard);
            employee.setBranch(branch);
            this.employeeRepository.saveAndFlush(employee);

            String employeeFullName = employee.getFirstName() + " " + employee.getLastName();

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    employee.getClass().getSimpleName(),employeeFullName))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }

    @Override
    public String exportProductiveEmployees() {
        StringBuilder exportResult = new StringBuilder();

        List<Employee> employees = this.employeeRepository.exportEmployees();

        employees.forEach(employee -> {

            exportResult
                    .append(String.format("Name: %s %s", employee.getFirstName(), employee.getLastName()))
                    .append(System.lineSeparator())
                    .append(String.format("Position: %s", employee.getPosition()))
                    .append(System.lineSeparator())
                    .append(String.format("Card Number: %s", employee.getCard().getNumber()))
                    .append(System.lineSeparator())
                    .append("-------------------------")
                    .append(System.lineSeparator());
        });

        return exportResult.toString().trim();
    }
}
