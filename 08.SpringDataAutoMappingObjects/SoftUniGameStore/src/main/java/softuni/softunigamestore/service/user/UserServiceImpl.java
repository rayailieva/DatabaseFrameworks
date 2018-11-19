package softuni.softunigamestore.service.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.softunigamestore.domain.dtos.user.UserLoginDto;
import softuni.softunigamestore.domain.dtos.user.UserLogoutDto;
import softuni.softunigamestore.domain.dtos.user.UserRegisterDto;
import softuni.softunigamestore.domain.dtos.view.GameTitleAndPriceViewDto;
import softuni.softunigamestore.domain.entities.Role;
import softuni.softunigamestore.domain.entities.User;
import softuni.softunigamestore.repository.UserRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        javax.validation.Validator validator =  Validation
                .byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .getValidator();

        StringBuilder sb = new StringBuilder();
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            sb.append("Passwords don't match.");
        } else if (validator.validate(userRegisterDto).size() > 0) {
            for (ConstraintViolation<UserRegisterDto> violation : validator.validate(userRegisterDto)) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
        } else {
            User entity =  this.userRepository.findByEmail(userRegisterDto.getEmail());

            if (entity != null) {
                sb.append("User already exists.");
                return sb.toString();
            }

            entity = this.modelMapper.map(userRegisterDto, User.class);

            if (this.userRepository.count() == 0) {
                entity.setRole(Role.ADMIN);
            } else {
                entity.setRole(Role.USER);
            }

            this.userRepository.saveAndFlush(entity);

            sb.append(String.format("%s was registered", entity.getFullName()));
        }

        return sb.toString().trim();
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        javax.validation.Validator validator = Validation
                .byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .getValidator();

        Set<ConstraintViolation<UserLoginDto>> violations = validator.validate(userLoginDto);

        StringBuilder sb = new StringBuilder();
        if (violations.size() > 0) {
            for (ConstraintViolation<UserLoginDto> violation : violations) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
        } else {
            User entity = this.userRepository.findByEmail(userLoginDto.getEmail());

            if (entity == null) {
                return sb.append("User does not exist.").append(System.lineSeparator()).toString();
            } else if(!entity.getPassword().equals(userLoginDto.getPassword())) {
                return sb.append("Wrong password.").append(System.lineSeparator()).toString();
            }

            sb.append(String.format("Successfully logged in %s", entity.getFullName())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String logoutUser(UserLogoutDto userLogoutDto) {
        StringBuilder sb = new StringBuilder();
        User entity = this.userRepository.findByEmail(userLogoutDto.getEmail());

        if (entity == null) {
            return sb.append("User does not exist.").append(System.lineSeparator()).toString();
        }

        sb.append(String.format("User %s successfully logged out", entity.getFullName()));

        return sb.toString();
    }

    @Override
    public Set<GameTitleAndPriceViewDto> getOwnedGamesTitle(Long id) {
        if(id != null){
            return this.userRepository
                    .findById(id)
                    .map(User::getGames)
                    .stream()
                    .map(game -> modelMapper.map(game, GameTitleAndPriceViewDto.class))
                    .collect(Collectors.toSet());
        }
        return new LinkedHashSet<>();
    }

}
