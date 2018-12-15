package fastfood.service;

import fastfood.domain.dtos.EmployeeImportDto;

public interface EmployeeService {

    void importCustomers(EmployeeImportDto[] employeeImportDtos);
}
