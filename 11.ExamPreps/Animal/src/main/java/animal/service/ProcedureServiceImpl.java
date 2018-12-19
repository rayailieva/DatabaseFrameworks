package animal.service;

import animal.domain.dtos.procedures.AidImportDto;
import animal.domain.dtos.procedures.ProcedureImportDto;
import animal.domain.dtos.procedures.ProcedureImportRootDto;
import animal.domain.entities.*;
import animal.repository.*;
import animal.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final AnimalAidRepository animalAidRepository;
    private final AnimalRepository animalRepository;
    private final PassportRepository passportRepository;
    private final VetRepository vetRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository, AnimalAidRepository animalAidRepository, AnimalRepository animalRepository, PassportRepository passportRepository, VetRepository vetRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.procedureRepository = procedureRepository;
        this.animalAidRepository = animalAidRepository;
        this.animalRepository = animalRepository;
        this.passportRepository = passportRepository;
        this.vetRepository = vetRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importProcedures(ProcedureImportRootDto procedureImportRootDto) {

        for(ProcedureImportDto procedureImportDto : procedureImportRootDto.getProcedureImportDtos()){

            Passport passport = this.passportRepository.findOneBySerialNumber(procedureImportDto.getAnimal()).orElse(null);
            Animal animal = this.animalRepository.findOneByPassport(passport).orElse(null);
            Vet vet = this.vetRepository.findOneByName(procedureImportDto.getVet()).orElse(null);

            if (!this.validatorUtil.isValid(procedureImportDto) || vet == null || animal == null) {
                System.out.println("error");
                continue;
            }

            Procedure procedure = this.modelMapper.map(procedureImportDto, Procedure.class);
            procedure.setVet(vet);
            procedure.setAnimal(animal);

            List<AnimalAid> animalAids = new ArrayList<>();
            for(AidImportDto aidImportDto : procedureImportDto.getAnimalAids().getAidImportDtos()){

                AnimalAid animalAid = this.animalAidRepository.findOneByName(aidImportDto.getName()).orElse(null);

                if(animalAid == null){
                    System.out.println("error");
                    continue;
                }

                animalAid = this.modelMapper.map(aidImportDto, AnimalAid.class);
                animalAids.add(animalAid);
            }


            procedure.setServices(animalAids);
            this.procedureRepository.saveAndFlush(procedure);
        }
    }
}
