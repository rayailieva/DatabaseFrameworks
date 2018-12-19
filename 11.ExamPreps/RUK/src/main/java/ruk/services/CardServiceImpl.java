package ruk.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruk.domain.dtos.cars.CardImportDto;
import ruk.domain.dtos.cars.CardImportRootDto;
import ruk.domain.entities.BankAccount;
import ruk.domain.entities.Card;
import ruk.repository.BankAccountRepository;
import ruk.repository.CardRepository;
import ruk.util.ValidatorUtil;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, BankAccountRepository bankAccountRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.cardRepository = cardRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importCards(CardImportRootDto cardImportRootDto) {

        for(CardImportDto cardImportDto : cardImportRootDto.getCardImportDtos()){
            if(!this.validatorUtil.isValid(cardImportDto)){
                System.out.println("Error: Incorrect Data!");
                continue;
            }

            BankAccount bankAccount = this.bankAccountRepository.findByAccountNumber(cardImportDto.getAccountNumber())
                    .orElse(null);

            Card card = this.modelMapper.map(cardImportDto, Card.class);

            if(bankAccount != null) {
                card.setBankAccount(bankAccount);
            }

            this.cardRepository.saveAndFlush(card);
        }
    }
}
