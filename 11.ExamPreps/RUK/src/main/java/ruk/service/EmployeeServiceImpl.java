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
    public void importEmployees(EmployeeImportDto[] employeeImportDtos) {
        for(EmployeeImportDto employeeImportDto : employeeImportDtos){

            String[] names = employeeImportDto.getFull_name().split("\\s+");

            Branch branch = this.branchRepository.findOneByName(employeeImportDto.getBranch_name());
            if(branch == null){
                System.out.println("Incorrect data!");
                continue;
            }
            Employee employee = this.modelMapper.map(employeeImportDto, Employee.class);

            employee.setFirstName(names[0]);
            employee.setLastName(names[1]);
            employee.setBranch(branch);

            this.employeeRepository.saveAndFlush(employee);
        }
    }
}
