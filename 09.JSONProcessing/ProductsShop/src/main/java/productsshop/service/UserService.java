package productsshop.service;

import productsshop.domain.dtos.view.UserFirstAndLastNamesAndSoldProductsDto;
import productsshop.domain.dtos.seedDatabase.UserSeedDto;
import productsshop.domain.dtos.view.UsersWithSalesListDto;

import java.util.List;


public interface UserService {

    void seedUsers(UserSeedDto[] userDtos);

    List<UserFirstAndLastNamesAndSoldProductsDto> getSuccessfulSellers();


    UsersWithSalesListDto getSellsByUser();
}
