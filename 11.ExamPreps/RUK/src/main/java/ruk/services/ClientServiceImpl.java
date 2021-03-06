package ruk.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruk.domain.dtos.ClientImportDto;
import ruk.domain.entities.Client;
import ruk.domain.entities.Employee;
import ruk.repository.ClientRepository;
import ruk.repository.EmployeeRepository;
import ruk.util.ValidatorUtil;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importClients(ClientImportDto[] clientImportDtos) {

        for(ClientImportDto clientImportDto : clientImportDtos){
            if(!this.validatorUtil.isValid(clientImportDto)){
                System.out.println("Error: Incorrect Data!");
                continue;
            }

            String[] employeeNames = clientImportDto.getEmployee().split("\\s+");
            String fullName = String.format("%s %s", employeeNames[0], employeeNames[1]);

            Employee employee = this.employeeRepository.findByFirstNameAndLastName(employeeNames[0], employeeNames[1]);

            Client client = this.modelMapper.map(clientImportDto, Client.class);
            client.setFullName(fullName);
            client.setBankAccount(null);

            employee.getClients().add(client);

            this.clientRepository.saveAndFlush(client);
            this.employeeRepository.saveAndFlush(employee);
        }
    }
}
