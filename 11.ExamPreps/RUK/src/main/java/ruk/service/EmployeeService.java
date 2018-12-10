package ruk.service;

import ruk.domain.dtos.EmployeeImportDto;

public interface EmployeeService {

    void importEmployees(EmployeeImportDto[] employeeImportDtos);
}
