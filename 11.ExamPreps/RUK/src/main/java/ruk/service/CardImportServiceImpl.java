package ruk.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruk.domain.dtos.CardImportDto;
import ruk.domain.dtos.CardImportRootDto;
import ruk.domain.entities.BankAccount;
import ruk.domain.entities.Card;
import ruk.repository.BankAccountRepository;
import ruk.repository.CardRepository;
import ruk.util.ValidatorUtil;

@Service
public class CardImportServiceImpl implements CardService{

    private final CardRepository cardRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public CardImportServiceImpl(CardRepository cardRepository, BankAccountRepository bankAccountRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.cardRepository = cardRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importCards(CardImportRootDto cardImportRootDto) {
        for(CardImportDto cardImportDto : cardImportRootDto.getCardImportDtos()){
            if(!this.validatorUtil.isValid(cardImportDto)){
                this.validatorUtil.violations(cardImportDto)
                        .forEach(System.out::println);
                continue;
            }
            BankAccount bankAccount = this.bankAccountRepository.findOneByAccountNumber(cardImportDto.getAccountNumber());

            if(bankAccount == null){
                System.out.println("error!");
                continue;
            }
            Card card = this.modelMapper.map(cardImportDto, Card.class);
            card.setBankAccount(bankAccount);

            this.cardRepository.saveAndFlush(card);

            bankAccount.getCards().add(card);
        }
    }
}
