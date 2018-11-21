package productsshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productsshop.domain.dtos.ProductInRangeDto;
import productsshop.domain.dtos.ProductNamePriceBuyerFirstAndLastNamesDto;
import productsshop.domain.dtos.UserFirstAndLastNamesAndSoldProductsDto;
import productsshop.domain.dtos.UserSeedDto;
import productsshop.domain.entities.User;
import productsshop.repository.UserRepository;
import productsshop.util.ValidatorUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

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
    public void seedUsers(UserSeedDto[] userSeedDtos) {
        for (UserSeedDto userSeedDto : userSeedDtos) {
            if (!this.validatorUtil.isValid(userSeedDto)) {
                this.validatorUtil.violations(userSeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));

                continue;
            }

            User entity = this.modelMapper.map(userSeedDto, User.class);

            this.userRepository.saveAndFlush(entity);
        }
    }

    @Override
    public List<UserFirstAndLastNamesAndSoldProductsDto> getSuccessfulSellers() {
        return this.userRepository
                .getAllBySellContainsProduct_Buyer()
                .stream()
                .map(user -> {
                    final UserFirstAndLastNamesAndSoldProductsDto userDto =
                            this.modelMapper.map(user, UserFirstAndLastNamesAndSoldProductsDto.class);
                    userDto.setSoldProducts(user
                            .getSell()
                            .stream()
                            .filter(sale -> sale.getBuyer() != null)
                            .map(sale -> this.modelMapper.map(sale, ProductNamePriceBuyerFirstAndLastNamesDto.class))
                            .collect(Collectors.toList()));
                    return userDto;
                })
                .collect(Collectors.toList());
    }


}
