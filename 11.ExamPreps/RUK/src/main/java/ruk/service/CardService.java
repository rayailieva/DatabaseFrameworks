package ruk.service;

import ruk.domain.dtos.CardImportRootDto;

public interface CardService {

    void importCards(CardImportRootDto cardImportRootDto);
}
