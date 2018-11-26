package cardealer.service;

import cardealer.domain.dtos.CustomerImportRootDto;
import cardealer.domain.dtos.CustomerPurchaseExportRootDto;
import cardealer.domain.dtos.CustomersExportRootDto;
import cardealer.domain.entities.Customer;

import java.util.List;

public interface CustomerService {

    void importCustomers(CustomerImportRootDto customerImportRootDto);

    CustomersExportRootDto getAllSortedByBirthday();

    List<Customer> getAllCustomers();

    CustomerPurchaseExportRootDto getCustomersPurchases();
}
