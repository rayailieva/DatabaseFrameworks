package productshopxml.service;

import productshopxml.domain.dtos.binding.UserSeedDto;
import productshopxml.domain.dtos.binding.UserSeedRootDto;
import productshopxml.domain.dtos.view.UsersProductsRootDto;

public interface UserService {

    void seedUsers(UserSeedRootDto userSeedRootDto);


}
