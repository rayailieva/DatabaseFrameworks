package softuni.mostwanted.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mostwanted.domain.dtos.json.DistrictImportDto;
import softuni.mostwanted.domain.entities.District;
import softuni.mostwanted.repository.DistrictRepository;
import softuni.mostwanted.util.ValidatorUtil;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.districtRepository = districtRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
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
