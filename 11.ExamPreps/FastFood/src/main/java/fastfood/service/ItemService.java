package fastfood.service;

import fastfood.domain.dtos.ItemImportDto;

public interface ItemService {

    void importItems(ItemImportDto[] itemImportDtos);
}
