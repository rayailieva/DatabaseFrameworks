package cardealer.service;

import cardealer.domain.dtos.SupplierExportRootDto;
import cardealer.domain.dtos.SupplierImportRootDto;

public interface SupplierService {

    void importSuppliers(SupplierImportRootDto supplierImportRootDto);

    SupplierExportRootDto getAllLocalSuppliers();
}
