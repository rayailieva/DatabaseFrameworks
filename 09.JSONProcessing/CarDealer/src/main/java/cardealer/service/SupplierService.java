package cardealer.service;

import cardealer.domain.dtos.binding.SupplySeedDto;
import cardealer.domain.dtos.view.SupplierViewDto;

import java.util.List;

public interface SupplierService {
    void seedSuppliers(SupplySeedDto[] supplySeedDtos);

    List<SupplierViewDto> getLocalSuppliers();
}
