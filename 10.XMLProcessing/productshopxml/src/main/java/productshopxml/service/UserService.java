package productshopxml.service;

import productshopxml.domain.dtos.binding.UserSeedDto;
import productshopxml.domain.dtos.binding.UserSeedRootDto;

public interface UserService {

    void seedUsers(UserSeedRootDto userSeedRootDto);
}
