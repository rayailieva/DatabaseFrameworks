package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends BillingDetail {

    private String cardType;
    private byte expirationMonth;
    private byte expirationYear;

    @Column(name = "type")
    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Column(name="expiration_month")
    public byte getExpirationMonth() {
        return this.expirationMonth;
    }

    public void setExpirationMonth(byte expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    @Column(name="expiration_year")
    public byte getExpirationYear() {
        return this.expirationYear;
    }

    public void setExpirationYear(byte expirationYear) {
        this.expirationYear = expirationYear;
    }
}
