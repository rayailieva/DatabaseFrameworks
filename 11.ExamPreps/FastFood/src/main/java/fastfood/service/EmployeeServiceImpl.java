package fastfood.service;

import fastfood.domain.dtos.EmployeeImportDto;
import fastfood.domain.entities.Employee;
import fastfood.domain.entities.Position;
import fastfood.repository.EmployeeRepository;
import fastfood.repository.PositionRepository;
import fastfood.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importCustomers(EmployeeImportDto[] employeeImportDtos) {

        for(EmployeeImportDto employeeImportDto : employeeImportDtos){
            if(!this.validationUtil.isValid(employeeImportDto)){
                System.out.println("Error: Invalid data.");
                continue;
            }

            Position position = this.positionRepository
                    .findByName(employeeImportDto.getPosition()).orElse(null);


            if(position == null ){
                position = new Position();
                position.setName(employeeImportDto.getPosition());

                if(!this.validationUtil.isValid(position)){
                    System.out.println("error");
                    continue;
                }
                this.positionRepository.saveAndFlush(position);
            }

            Employee employee = this.modelMapper.map(employeeImportDto, Employee.class);
            employee.setPosition(position);
            this.employeeRepository.saveAndFlush(employee);

        }
    }
}
