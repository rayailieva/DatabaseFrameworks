package productsshop.service;

import productsshop.domain.dtos.UserSeedDto;

public interface UserService {

    void seedUsers(UserSeedDto[] userDtos);
}
