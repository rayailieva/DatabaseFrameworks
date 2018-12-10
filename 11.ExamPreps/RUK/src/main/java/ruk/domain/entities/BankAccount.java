package ruk.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "bank_accounts")
public class BankAccount extends BaseEntity {

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToOne(targetEntity = Client.class)
    private Client client;

    @OneToMany(mappedBy = "bankAccount", targetEntity = Card.class, fetch = FetchType.EAGER)
    private List<Card> cards;

    public BankAccount(){}

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
