package cardealer.service;

import cardealer.domain.dtos.binding.SupplySeedDto;
import cardealer.domain.dtos.view.SupplierViewDto;
import cardealer.domain.entities.Supplier;
import cardealer.repository.SupplierRepository;
import cardealer.util.ValidatorUtil;
import cardealer.util.ValidatorUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
        this.validatorUtil = new ValidatorUtilImpl();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void seedSuppliers(SupplySeedDto[] supplySeedDtos) {
        for(SupplySeedDto supplySeedDto : supplySeedDtos){
            if(!this.validatorUtil.isValid(supplySeedDto)){
                this.validatorUtil.violations(supplySeedDto)
                    .forEach(violation -> System.out.println(violation.getMessage()));

                continue;
                }

            Supplier entity = this.modelMapper.map(supplySeedDto, Supplier.class);
            this.supplierRepository.saveAndFlush(entity);
            }
        }

    @Override
    public List<SupplierViewDto> getLocalSuppliers() {
       return this.supplierRepository.getLocalSuppliers();
    }
}

