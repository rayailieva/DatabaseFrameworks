package cardealer.service;

import cardealer.domain.dtos.view.SaleDetailsViewDto;

import java.util.List;

public interface SalesService {
    void generateSales();

    List<SaleDetailsViewDto> getSalesDetails();
}
