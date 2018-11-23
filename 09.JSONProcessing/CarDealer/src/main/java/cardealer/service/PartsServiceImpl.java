package cardealer.service;

import cardealer.domain.dtos.PartsSeedDto;
import cardealer.domain.entities.Part;
import cardealer.domain.entities.Supplier;
import cardealer.repository.PartsRepository;
import cardealer.repository.SupplierRepository;
import cardealer.util.ValidatorUtil;
import cardealer.util.ValidatorUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PartsServiceImpl implements PartsService {

    private final PartsRepository partsRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    public PartsServiceImpl(PartsRepository partsRepository, SupplierRepository supplierRepository) {
        this.partsRepository = partsRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = new ModelMapper();
        this.validatorUtil = new ValidatorUtilImpl();
    }

    @Override
    public void seedParts(PartsSeedDto[] partsSeedDtos) {
        for(PartsSeedDto partsSeedDto : partsSeedDtos){
            if(!this.validatorUtil.isValid(partsSeedDto)){
                this.validatorUtil.violations(partsSeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));

                continue;
            }

            Part entity = this.modelMapper.map(partsSeedDto, Part.class);
            entity.setSupplier(this.getRandomSupplier());


            this.partsRepository.saveAndFlush(entity);
        }

    }

    @Override
    public List<Part> getAllParts() {
        return this.partsRepository.findAll();
    }

    private Supplier getRandomSupplier() {
        Random random = new Random();

        return this.supplierRepository.getOne(random.nextInt((int) (this.supplierRepository.count() - 1)) + 1);
    }
}
