package ruk.domain.entities;

import ruk.domain.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "cards")
public class Card extends BaseEntity {

    private String cardNumber;
    private String cardStatus;
    private BankAccount bankAccount;

    public Card(){}

    @Column(name = "card_number", nullable = false)
    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Column(name = "card_status", nullable = false)
    public String getCardStatus() {
        return this.cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    @ManyToOne(targetEntity = BankAccount.class)
    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
