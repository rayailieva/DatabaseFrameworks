package hiberspring.domain.dtos.products;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportDto {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "clients")
    private Long clients;
    @XmlElement(name = "branch")
    private String branch;

    public ProductImportDto() {
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public Long getClients() {
        return this.clients;
    }

    public void setClients(Long clients) {
        this.clients = clients;
    }

    @NotNull
    public String getBranch() {
        return this.branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
