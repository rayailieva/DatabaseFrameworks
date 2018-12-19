package ruk.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruk.domain.dtos.bankaccounts.BankAccountImportDto;
import ruk.domain.dtos.bankaccounts.BankAccountImportRootDto;
import ruk.domain.entities.BankAccount;
import ruk.domain.entities.Client;
import ruk.repository.BankAccountRepository;
import ruk.repository.ClientRepository;
import ruk.util.ValidatorUtil;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ClientRepository clientRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.bankAccountRepository = bankAccountRepository;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importBankAccounts(BankAccountImportRootDto bankAccountImportRootDto) {

        for (BankAccountImportDto bankAccountImportDto : bankAccountImportRootDto.getBankAccountImportDtos()) {
            if (!this.validatorUtil.isValid(bankAccountImportDto)) {
                System.out.println("Error: Incorrect Data!");
                continue;
            }

           // List<Client> clients = this.clientRepository.findAllByFullName(bankAccountImportDto.getClient());
           // if (clients.isEmpty()) {
           //     System.out.println("error!");
           //     continue;
           // }
//
            BankAccount bankAccount = this.modelMapper.map(bankAccountImportDto, BankAccount.class);
           // bankAccount.setClient(clients.get(0));

            this.bankAccountRepository.saveAndFlush(bankAccount);

           // for (Client client : clients) {
           //     client.setBankAccount(bankAccount);
           //     this.clientRepository.saveAndFlush(client);
           // }

        }
    }
}
