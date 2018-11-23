package productsshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class UsersWithSalesListDto {

    @Expose
    private Integer usersCount;

    @Expose
    private List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> users;


    public UsersWithSalesListDto() {
        users = new ArrayList<>();
    }

    public Integer getUsersCount() {
        return this.usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> users) {
        this.users = users;
    }
}
