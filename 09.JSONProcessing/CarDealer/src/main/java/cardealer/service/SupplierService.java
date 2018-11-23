package cardealer.service;

import cardealer.domain.dtos.SupplySeedDto;

public interface SupplierService {
    void seedSuppliers(SupplySeedDto[] supplySeedDtos);
}
