package cardealer.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierExportDto {

    @XmlAttribute(name = "id")
    private Integer id;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "parts-count")
    private Long partsCount;

    public SupplierExportDto(){
    }

    public SupplierExportDto(Integer id, String name, Long partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPartsCount() {
        return this.partsCount;
    }

    public void setPartsCount(Long partsCount) {
        this.partsCount = partsCount;
    }
}
