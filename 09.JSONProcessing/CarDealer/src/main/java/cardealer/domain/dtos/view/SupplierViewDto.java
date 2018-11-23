package cardealer.domain.dtos.view;

import com.google.gson.annotations.Expose;

public class SupplierViewDto {

    @Expose
    private Integer id;

    @Expose
    private String name;

    @Expose
    private Long partsCount;

    public SupplierViewDto(){
    }

    public SupplierViewDto(Integer id, String name, Long partsCount) {
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
