package ruk.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "cards")
public class Card extends BaseEntity {

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "card_status", nullable = false)
    private String cardStatus;

    @ManyToOne(targetEntity = BankAccount.class)
    private BankAccount bankAccount;

    public Card(){}

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardStatus() {
        return this.cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
