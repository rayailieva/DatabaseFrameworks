package cardealer.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersExportRootDto {

    @XmlElement(name = "customer")
    private List<CustomersExportDto> customersExportDtos;

    public CustomersExportRootDto(){
    }

    public List<CustomersExportDto> getCustomersExportDtos() {
        return this.customersExportDtos;
    }

    public void setCustomersExportDtos(List<CustomersExportDto> customersExportDtos) {
        this.customersExportDtos = customersExportDtos;
    }
}
