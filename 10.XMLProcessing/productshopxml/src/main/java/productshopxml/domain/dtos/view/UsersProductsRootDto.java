package productshopxml.domain.dtos.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersProductsRootDto {

    @XmlElement(name = "users")
    private List<UsersProductsDto> usersProductsDtos;

    public UsersProductsRootDto(){
    }

    public List<UsersProductsDto> getUsersProductsDtos() {
        return this.usersProductsDtos;
    }

    public void setUsersProductsDtos(List<UsersProductsDto> usersProductsDtos) {
        this.usersProductsDtos = usersProductsDtos;
    }
}
