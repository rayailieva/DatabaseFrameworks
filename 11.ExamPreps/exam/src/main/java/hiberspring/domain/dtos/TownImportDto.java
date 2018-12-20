package hiberspring.domain.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownImportDto {

    @Expose
    private String name;
    @Expose
    private Long population;

    public TownImportDto() {
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public Long getPopulation() {
        return this.population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}
