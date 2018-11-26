package cardealer.service;

import cardealer.domain.dtos.SupplierExportDto;
import cardealer.domain.dtos.SupplierExportRootDto;
import cardealer.domain.entities.Car;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import cardealer.domain.dtos.SupplierImportDto;
import cardealer.domain.dtos.SupplierImportRootDto;
import cardealer.domain.entities.Supplier;
import cardealer.repository.SupplierRepository;
import cardealer.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importSuppliers(SupplierImportRootDto supplierImportRootDto) {
        for (SupplierImportDto supplierImportDto : supplierImportRootDto.getSupplierImportDtos()) {
            if (!this.validationUtil.isValid(supplierImportDto)) {
                System.out.println("Something went wrong");

                continue;
            }

            Supplier supplierEntity = this.modelMapper.map(supplierImportDto, Supplier.class);

            this.supplierRepository.saveAndFlush(supplierEntity);
        }
    }

    @Override
    public SupplierExportRootDto getAllLocalSuppliers() {
        List<Supplier> supplierEntities = this.supplierRepository.getAllLocalSuppliers();

        List<SupplierExportDto> supplierExportDtos = new ArrayList<>();

        for(Supplier supplier : supplierEntities){
            SupplierExportDto supplierExportDto = this.modelMapper.map(supplier, SupplierExportDto.class);
            supplierExportDtos.add(supplierExportDto);
        }

        SupplierExportRootDto supplierExportRootDto = new SupplierExportRootDto();
        supplierExportRootDto.setSupplierExportDtos(supplierExportDtos);

        return supplierExportRootDto;
    }
}