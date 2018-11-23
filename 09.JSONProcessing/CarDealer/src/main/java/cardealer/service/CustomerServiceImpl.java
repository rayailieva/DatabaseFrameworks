package cardealer.service;

import cardealer.domain.dtos.binding.CustomersSeedDto;
import cardealer.domain.dtos.view.OrderedCustomersDto;
import cardealer.domain.entities.Customer;
import cardealer.repository.CustomersRepository;
import cardealer.util.ValidatorUtil;
import cardealer.util.ValidatorUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomersRepository customersRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
        this.validatorUtil = new ValidatorUtilImpl();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void seedCustomers(CustomersSeedDto[] customersSeedDtos) {
        for(CustomersSeedDto customersSeedDto : customersSeedDtos){
            if(!this.validatorUtil.isValid(customersSeedDto)){
                this.validatorUtil.violations(customersSeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));

                continue;
            }

            Customer entity = this.modelMapper.map(customersSeedDto, Customer.class);
            this.customersRepository.saveAndFlush(entity);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customersRepository.findAll();
    }

    @Override
    public List<OrderedCustomersDto> getOrderedCustomers() {
        return this.customersRepository
                .getOrderedCustomers()
                .stream()
                .map(customer -> this.modelMapper.map(customer, OrderedCustomersDto.class)
                   )
                .collect(Collectors.toList());
    }


}
