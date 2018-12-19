package animal.service;

import animal.domain.dtos.vets.VetImportDto;
import animal.domain.dtos.vets.VetImportRootDto;
import animal.domain.entities.Vet;
import animal.repository.VetRepository;
import animal.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VetServiceImpl implements VetService{

    private final VetRepository vetRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public VetServiceImpl(VetRepository vetRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.vetRepository = vetRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importVets(VetImportRootDto vetImportRootDto) {

        for(VetImportDto vetImportDto : vetImportRootDto.getVetImportDtos()){
            if (!this.validatorUtil.isValid(vetImportDto)) {
                System.out.println("invalid vet!");
                continue;
            }

            Vet vet = this.modelMapper.map(vetImportDto, Vet.class);
            this.vetRepository.saveAndFlush(vet);
        }
    }
}
