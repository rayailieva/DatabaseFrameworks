package cardealer.service;

import cardealer.domain.dtos.binding.CustomersSeedDto;
import cardealer.domain.dtos.view.OrderedCustomersDto;
import cardealer.domain.entities.Customer;

import java.util.List;

public interface CustomerService {

    void seedCustomers(CustomersSeedDto[] customersSeedDtos);

    List<Customer> getAllCustomers();

    List<OrderedCustomersDto> getOrderedCustomers();
}
