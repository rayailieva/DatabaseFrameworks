package softuni.mostwanted.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mostwanted.domain.dtos.binding.json.TownImportDto;
import softuni.mostwanted.domain.entities.Town;
import softuni.mostwanted.repository.TownRepository;
import softuni.mostwanted.util.ValidatorUtil;
import softuni.mostwanted.util.ValidatorUtilImpl;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validationUtil;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.modelMapper = new ModelMapper();
        this.validationUtil = new ValidatorUtilImpl();
    }

    @Override
    public void importTowns(TownImportDto[] townImportDtos) {
        for(TownImportDto townImportDto : townImportDtos){
            if(!this.validationUtil.isValid(townImportDto)){
                this.validationUtil.violations(townImportDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));
                continue;
            }

            Town entity = this.modelMapper.map(townImportDto, Town.class);
            this.townRepository.saveAndFlush(entity);
        }
    }
}
