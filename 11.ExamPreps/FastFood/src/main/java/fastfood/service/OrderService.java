package fastfood.service;

import fastfood.domain.dtos.orders.OrderImportRootDto;

public interface OrderService {

    void importOrders(OrderImportRootDto orderImportRootDto);
}
