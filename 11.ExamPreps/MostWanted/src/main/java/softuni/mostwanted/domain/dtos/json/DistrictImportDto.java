package softuni.mostwanted.domain.dtos.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class DistrictImportDto {

    @Expose
    @NotNull
    private String name;

    @Expose
    private String townName;

    public DistrictImportDto(){}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return this.townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
