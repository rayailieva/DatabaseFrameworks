package cardealer.service;

import cardealer.domain.dtos.view.CarViewShortDto;
import cardealer.domain.dtos.view.SaleDetailsViewDto;
import cardealer.domain.entities.Car;
import cardealer.domain.entities.Customer;
import cardealer.domain.entities.Part;
import cardealer.domain.entities.Sale;
import cardealer.repository.SalesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SalesService {
    private final SalesRepository salesRepository;
    private final ModelMapper modelMapper;
    private final CarService carService;
    private final CustomerService customerService;

    @Autowired
    public SalesServiceImpl(SalesRepository salesRepository,CarService carService, CustomerService customerService) {
        this.salesRepository = salesRepository;
        this.modelMapper = new ModelMapper();
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public void generateSales() {
        final List<Car> cars = this.carService.getAllCars();
        final List<Customer> customers = this.customerService.getAllCustomers();
        final Random random = new Random();
        final Double[] discounts = {0d, 0.05d, 0.1d, 0.15d, 0.2d, 0.3d, 0.4d, 0.5d};

        List<Sale> sales = new LinkedList<>();

        for (final Car car : cars) {
            Sale sale = new Sale();
            sale.setCar(car);
            sale.setCustomer(customers.get(random.nextInt(customers.size())));
            sale.setDiscount(discounts[random.nextInt(discounts.length)]);
            this.salesRepository.saveAndFlush(sale);
            sales.add(sale);
        }

        this.salesRepository.saveAll(sales);
    }

    @Override
    public List<SaleDetailsViewDto> getSalesDetails(){
        return this.salesRepository
                .findAll()
                .stream()
                .map(sale -> {
                    SaleDetailsViewDto saleDetailsViewDto = new SaleDetailsViewDto();
                    saleDetailsViewDto.setCar(this.modelMapper.map(sale.getCar(), CarViewShortDto.class));
                    saleDetailsViewDto.setCustomerName(sale.getCustomer().getName());
                    saleDetailsViewDto.setDiscount(sale.getDiscount());

                    saleDetailsViewDto.setPrice(sale
                            .getCar()
                            .getParts()
                            .stream()
                            .map(Part::getPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add));

                    saleDetailsViewDto.setPriceWithDiscount(saleDetailsViewDto
                            .getPrice()
                            .multiply(BigDecimal.valueOf(1.0d - saleDetailsViewDto.getDiscount())));
                    return saleDetailsViewDto;
                        }).collect(Collectors.toList());
    }

}
