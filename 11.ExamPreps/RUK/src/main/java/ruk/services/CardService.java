package ruk.services;

import ruk.domain.dtos.cars.CardImportRootDto;

public interface CardService {

    void importCards(CardImportRootDto cardImportRootDto);
}
