package ruk.domain.dtos.cars;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardImportDto {

    @XmlAttribute(name = "status")
    private String status;
    @XmlAttribute(name = "account-number")
    private String accountNumber;
    @XmlElement(name = "card-number")
    private String cardNumber;

    public CardImportDto(){}

    @NotNull
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @NotNull
    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
