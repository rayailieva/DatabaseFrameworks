package ruk.service;

import ruk.domain.dtos.EmployeeImportDto;

public interface EmployeeService {

    String importEmployees(EmployeeImportDto[] employeeImportDtos);
}
