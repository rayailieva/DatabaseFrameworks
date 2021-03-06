package productshopxml.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productshopxml.domain.dtos.binding.UserSeedDto;
import productshopxml.domain.dtos.binding.UserSeedRootDto;
import productshopxml.domain.dtos.view.ProductBuyerDto;
import productshopxml.domain.dtos.view.ProductBuyerRootDto;
import productshopxml.domain.dtos.view.UsersProductsDto;
import productshopxml.domain.dtos.view.UsersProductsRootDto;
import productshopxml.domain.entities.Product;
import productshopxml.domain.entities.User;
import productshopxml.repository.UserRepository;
import productshopxml.util.ValidatorUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Override
    public UsersProductsRootDto getUsersSoldProducts() {
        List<User> userEntities = this.userRepository.getAllBySellContainsProductBuyer();

        List<UsersProductsDto> usersProductsDtos = new ArrayList<>();
        for(User user : userEntities){
            UsersProductsDto usersProductsDto = this.modelMapper.map(user, UsersProductsDto.class);

            List<ProductBuyerDto> productBuyerDtos = new ArrayList<>();

            for(Product product : user.getSoldProducts()){
                ProductBuyerDto productBuyerDto = this.modelMapper.map(product, ProductBuyerDto.class);

                productBuyerDtos.add(productBuyerDto);
            }

            ProductBuyerRootDto productBuyerRootDto = new ProductBuyerRootDto();
            productBuyerRootDto.setProductView(productBuyerDtos);

            usersProductsDtos.add(usersProductsDto);
        }

        UsersProductsRootDto usersProductsRootDto = new UsersProductsRootDto();
        usersProductsRootDto.setUsersProductsDtos(usersProductsDtos);

        return usersProductsRootDto;
    }


}
