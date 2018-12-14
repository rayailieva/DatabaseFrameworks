package ruk.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruk.domain.dtos.BankAccountImportDto;
import ruk.domain.dtos.BankAccountImportRootDto;
import ruk.domain.entities.BankAccount;
import ruk.domain.entities.Client;
import ruk.repository.BankAccountRepository;
import ruk.repository.ClientRepository;
import ruk.util.ValidatorUtil;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService{

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
    public String importBankAccounts(BankAccountImportRootDto bankAccountImportRootDto) {
        StringBuilder importResult = new StringBuilder();

        for (BankAccountImportDto bankAccountImportDto : bankAccountImportRootDto.getBankAccountImportDtos()) {
            if (!this.validatorUtil.isValid(bankAccountImportDto)) {
                importResult.append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
                break;
            }

            List<Client> clients = this.clientRepository.findAllByFullName(bankAccountImportDto.getClient());
            if (clients.isEmpty()) {
                System.out.println("error!");
                continue;
            }

            BankAccount bankAccount = new BankAccount();
            bankAccount.setAccountNumber(bankAccountImportDto.getAccountNumber());
            bankAccount.setClient(clients.get(0));
            bankAccount.setBalance(bankAccountImportDto.getBalance());

            this.bankAccountRepository.saveAndFlush(bankAccount);

            for (Client client : clients) {
                client.setBankAccount(bankAccount);
                this.clientRepository.saveAndFlush(client);
            }

        }
            return importResult.toString().trim();

    }
}
