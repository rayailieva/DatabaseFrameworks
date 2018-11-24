package productshopxml.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productshopxml.domain.dtos.binding.UserSeedDto;
import productshopxml.domain.dtos.binding.UserSeedRootDto;
import productshopxml.domain.entities.User;
import productshopxml.repository.UserRepository;
import productshopxml.util.ValidatorUtil;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedUsers(UserSeedRootDto userSeedRootDto) {
        for(UserSeedDto userSeedDto : userSeedRootDto.getUserSeedDtos()){
            if(!this.validatorUtil.isValid(userSeedDto)){
                System.out.println("Something is wrong.");
                continue;
            }

            User userEntity = this.modelMapper.map(userSeedDto, User.class);
            this.userRepository.saveAndFlush(userEntity);
        }
    }
}
