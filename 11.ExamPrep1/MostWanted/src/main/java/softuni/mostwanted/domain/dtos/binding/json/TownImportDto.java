package softuni.mostwanted.domain.dtos.binding.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownImportDto {

    @Expose
    @NotNull
    private String name;

    public TownImportDto(){}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
