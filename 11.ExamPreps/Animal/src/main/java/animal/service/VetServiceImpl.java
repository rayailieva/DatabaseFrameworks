package animal.service;

import animal.domain.dtos.xml.VetImportDto;
import animal.domain.dtos.xml.VetImportRootDto;
import animal.domain.entities.Vet;
import animal.repository.VetRepository;
import animal.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public VetServiceImpl(VetRepository vetRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.vetRepository = vetRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importVets(VetImportRootDto vetImportRootDto) {
        for(VetImportDto vetImportDto : vetImportRootDto.getVetImportDtos()){
            if(!this.validatorUtil.isValid(vetImportDto)){
                this.validatorUtil.violations(vetImportDto)
                        .forEach(System.out::println);
                continue;
            }

            Vet entity = this.modelMapper.map(vetImportDto, Vet.class);
            this.vetRepository.saveAndFlush(entity);
        }
    }
}
