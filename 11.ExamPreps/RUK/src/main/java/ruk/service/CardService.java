package ruk.service;

import ruk.domain.dtos.CardImportRootDto;

public interface CardService {

    String importCards(CardImportRootDto cardImportRootDto);
}
