package cardealer.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerPurchaseExportRootDto {

    @XmlElement(name = "customer")
    private List<CustomerPurchaseExportDto> customersExportDto;

    public CustomerPurchaseExportRootDto(){}


    public List<CustomerPurchaseExportDto> getCustomersExportDto() {
        return this.customersExportDto;
    }

    public void setCustomersExportDto(List<CustomerPurchaseExportDto> customersExportDto) {
        this.customersExportDto = customersExportDto;
    }
}
