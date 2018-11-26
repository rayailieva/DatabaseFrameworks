package cardealer.service;

import cardealer.domain.dtos.*;
import cardealer.domain.entities.Customer;
import cardealer.domain.entities.Part;
import cardealer.repository.CustomerRepository;
import cardealer.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importCustomers(CustomerImportRootDto customerImportRootDto) {
        for(CustomerImportDto customerImportDto : customerImportRootDto.getCustomerImportDtos()){
            if(!validationUtil.isValid(customerImportDto)){
                System.out.println("Something went wrong");
                continue;
            }

            Customer customerEntity = this.modelMapper.map(customerImportDto, Customer.class);
            this.customerRepository.saveAndFlush(customerEntity);
        }
    }

    @Override
    public CustomersExportRootDto getAllSortedByBirthday() {
        List<Customer> customerEntities = this.customerRepository.getAllByBirthdate();

        List<CustomersExportDto> customersExportDtos = new ArrayList<>();
        for(Customer customer : customerEntities){
            CustomersExportDto customersExportDto = this.modelMapper.map(customer, CustomersExportDto.class);

            customersExportDtos.add(customersExportDto);
        }

        CustomersExportRootDto customersExportRootDto = new CustomersExportRootDto();
        customersExportRootDto.setCustomersExportDtos(customersExportDtos);

        return customersExportRootDto;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public CustomerPurchaseExportRootDto getCustomersPurchases() {
       List<Customer> customerEntities =  this.customerRepository.findAll();

            List<CustomerPurchaseExportDto> customerPurchaseExportDtos = new ArrayList<>();
               for(Customer customer : customerEntities) {

                   CustomerPurchaseExportDto customerDto = new CustomerPurchaseExportDto();
                   customerDto.setName(customer.getName());
                   customerDto.setBoughtCars(customer.getPurchases().size());

                   BigDecimal moneySpent = customer.getPurchases()
                           .stream()
                           .map(sale -> sale.getCar()
                                   .getParts()
                                   .stream()
                                   .map(Part::getPrice)
                                   .reduce(BigDecimal.ZERO, BigDecimal::add)
                           )
                           .reduce(BigDecimal.ZERO, BigDecimal::add);

                   customerDto.setSpentMoney(moneySpent);
                   customerPurchaseExportDtos.add(customerDto);
               }

               CustomerPurchaseExportRootDto customerPurchaseExportRootDto = new CustomerPurchaseExportRootDto();
               customerPurchaseExportRootDto.setCustomersExportDto(customerPurchaseExportDtos);

               return customerPurchaseExportRootDto;



    }
}
