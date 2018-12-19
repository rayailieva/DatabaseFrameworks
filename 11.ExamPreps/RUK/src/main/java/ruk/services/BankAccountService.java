package ruk.services;

import ruk.domain.dtos.bankaccounts.BankAccountImportRootDto;

public interface BankAccountService {

    void importBankAccounts(BankAccountImportRootDto bankAccountImportRootDto);
}
