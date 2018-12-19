package ruk.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruk.domain.dtos.EmployeeImportDto;
import ruk.domain.entities.Branch;
import ruk.domain.entities.Employee;
import ruk.repository.BranchRepository;
import ruk.repository.EmployeeRepository;
import ruk.util.ValidatorUtil;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper, Gson gson) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public void importEmployees(EmployeeImportDto[] employeeImportDtos) {

        for(EmployeeImportDto employeeImportDto : employeeImportDtos){
            if(!this.validatorUtil.isValid(employeeImportDto)){
                System.out.println("Error: Incorrect Data!");
                continue;
            }

            String[] names = employeeImportDto.getFullName().split("\\s+");

            Branch branch = this.branchRepository.findByName(employeeImportDto.getBranchName());
            Employee employee  = this.modelMapper.map(employeeImportDto, Employee.class);

            employee.setBranch(branch);
            employee.setFirstName(names[0]);
            employee.setLastName(names[1]);

            this.employeeRepository.saveAndFlush(employee);
        }
    }
}
