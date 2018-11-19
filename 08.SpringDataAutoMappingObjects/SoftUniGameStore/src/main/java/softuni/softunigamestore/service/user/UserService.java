package softuni.softunigamestore.service.user;

import softuni.softunigamestore.domain.dtos.user.UserLoginDto;
import softuni.softunigamestore.domain.dtos.user.UserLogoutDto;
import softuni.softunigamestore.domain.dtos.user.UserRegisterDto;
import softuni.softunigamestore.domain.dtos.view.GameTitleAndPriceViewDto;

import java.util.Set;

public interface UserService {

    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    String logoutUser(UserLogoutDto userLogoutDto);

    Set<GameTitleAndPriceViewDto> getOwnedGamesTitle(Long id);
}
