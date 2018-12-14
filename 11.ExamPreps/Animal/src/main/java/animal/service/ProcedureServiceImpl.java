package animal.service;

import animal.domain.dtos.procedures.AnimalAidXmlImportDto;
import animal.domain.dtos.procedures.ProcedureImportDto;
import animal.domain.dtos.procedures.ProcedureImportRootDto;
import animal.domain.entities.Animal;
import animal.domain.entities.AnimalAid;
import animal.domain.entities.Procedure;
import animal.domain.entities.Vet;
import animal.repository.AnimalAidRepository;
import animal.repository.AnimalRepository;
import animal.repository.ProcedureRepository;
import animal.repository.VetRepository;
import animal.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final AnimalRepository animalRepository;
    private final VetRepository vetRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository, AnimalRepository animalRepository, VetRepository vetRepository, AnimalAidRepository animalAidRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.procedureRepository = procedureRepository;
        this.animalRepository = animalRepository;
        this.vetRepository = vetRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importProcedures(ProcedureImportRootDto procedureImportRootDto) {

        for(ProcedureImportDto procedureImportDto : procedureImportRootDto.getProcedureImportDtos()){

            if(!this.validatorUtil.isValid(procedureImportDto)){
                System.out.println("something went wrong!");
                continue;
            }

            Animal animal = this.animalRepository.findByPassport(procedureImportDto.getAnimal()).orElse(null);
            Vet vet = this.vetRepository.findByName(procedureImportDto.getVet()).orElse(null);

            if(vet == null || animal == null){
                System.out.println("null error");
                continue;
            }

            Procedure procedure = this.modelMapper.map(procedureImportDto, Procedure.class);
            procedure.setAnimal(animal);
            procedure.setVet(vet);

            List<AnimalAid> animalAids = new ArrayList<>();
            for(AnimalAidXmlImportDto animalAidXmlImportDto : procedureImportDto.getAnimalAidXmlImportRootDto().getAnimalAidXmlImportDtos()){

                if(!this.validatorUtil.isValid(animalAidXmlImportDto)){
                    System.out.println("something went wrong");
                    continue;
                }

                AnimalAid animalAid = this.modelMapper.map(animalAidXmlImportDto, AnimalAid.class);
                animalAids.add(animalAid);
            }

            procedure.setServices(animalAids);
            this.procedureRepository.saveAndFlush(procedure);
        }
    }
}
