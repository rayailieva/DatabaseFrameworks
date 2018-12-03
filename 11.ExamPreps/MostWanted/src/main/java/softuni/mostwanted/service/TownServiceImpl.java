package softuni.mostwanted.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mostwanted.domain.dtos.json.TownImportDto;
import softuni.mostwanted.domain.entities.Town;
import softuni.mostwanted.repository.TownRepository;
import softuni.mostwanted.util.ValidatorUtil;

@Service
public class TownServiceImpl implements TownService{

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importTowns(TownImportDto[] townImportDtos) {
        for(TownImportDto townImportDto : townImportDtos){
            if(!this.validatorUtil.isValid(townImportDto)){
                this.validatorUtil.violations(townImportDto)
                        .forEach(System.out::println);
                continue;
            }

            Town entity = this.modelMapper.map(townImportDto, Town.class);
            this.townRepository.saveAndFlush(entity);
        }
    }
}
