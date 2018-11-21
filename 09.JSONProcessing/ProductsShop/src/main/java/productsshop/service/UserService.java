package productsshop.service;

import productsshop.domain.dtos.UserFirstAndLastNamesAndSoldProductsDto;
import productsshop.domain.dtos.UserSeedDto;

import java.util.List;


public interface UserService {

    void seedUsers(UserSeedDto[] userDtos);

    List<UserFirstAndLastNamesAndSoldProductsDto> getSuccessfulSellers();


}
