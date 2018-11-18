package softuni.softunigamestore.service;

import softuni.softunigamestore.domain.dtos.UserLoginDto;
import softuni.softunigamestore.domain.dtos.UserLogoutDto;
import softuni.softunigamestore.domain.dtos.UserRegisterDto;

public interface UserService {

    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    String logoutUser(UserLogoutDto userLogoutDto);


}
