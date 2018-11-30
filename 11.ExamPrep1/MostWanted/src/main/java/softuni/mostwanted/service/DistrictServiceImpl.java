package softuni.mostwanted.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.mostwanted.domain.dtos.binding.json.DistrictImportDto;
import softuni.mostwanted.domain.entities.District;
import softuni.mostwanted.repository.DistrictRepository;
import softuni.mostwanted.util.ValidatorUtil;
import softuni.mostwanted.util.ValidatorUtilImpl;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
        this.modelMapper = new ModelMapper();
        this.validatorUtil = new ValidatorUtilImpl();
    }

    @Override
    public void importDistricts(DistrictImportDto[] districtImportDtos) {
        for(DistrictImportDto districtImportDto : districtImportDtos){
            if(!this.validatorUtil.isValid(districtImportDto)){
                this.validatorUtil.violations(districtImportDto)
                        .forEach(System.out::println);
                continue;
            }

            District entity = this.modelMapper.map(districtImportDto, District.class);
            this.districtRepository.saveAndFlush(entity);
        }
    }
}
