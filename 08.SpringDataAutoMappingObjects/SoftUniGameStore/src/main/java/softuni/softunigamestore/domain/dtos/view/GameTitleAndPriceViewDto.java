package softuni.softunigamestore.domain.dtos.view;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class GameTitleAndPriceViewDto {

    private String title;
    private BigDecimal price;

    public GameTitleAndPriceViewDto(){
    }

    public GameTitleAndPriceViewDto(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }


    @NotNull(message = "Title cannot be null.")
    @Pattern(regexp = "([A-Z])[a-z]{4,100}", message = "Title has to begin with an uppercase letter and must have length between 3 and 100 symbols ")
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Min(0)
    @Digits(integer = 19, fraction = 2)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
