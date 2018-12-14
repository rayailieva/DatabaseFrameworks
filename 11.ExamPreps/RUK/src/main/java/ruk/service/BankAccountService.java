package ruk.service;

import ruk.domain.dtos.BankAccountImportRootDto;

public interface BankAccountService {

    String importBankAccounts(BankAccountImportRootDto bankAccountImportRootDto);
}
