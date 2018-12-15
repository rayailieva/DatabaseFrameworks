package fastfood.service;

import fastfood.domain.dtos.orders.ItemImportXmlDto;
import fastfood.domain.dtos.orders.OrderImportDto;
import fastfood.domain.dtos.orders.OrderImportRootDto;
import fastfood.domain.entities.Employee;
import fastfood.domain.entities.Item;
import fastfood.domain.entities.Order;
import fastfood.domain.entities.OrderItem;
import fastfood.repository.EmployeeRepository;
import fastfood.repository.ItemRepository;
import fastfood.repository.OrderItemIRepository;
import fastfood.repository.OrderRepository;
import fastfood.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderItemIRepository orderItemIRepository;
    private final EmployeeRepository employeeRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ItemRepository itemRepository, OrderItemIRepository orderItemIRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.orderItemIRepository = orderItemIRepository;
        this.employeeRepository = employeeRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public void importOrders(OrderImportRootDto orderImportRootDto) {

        for(OrderImportDto orderImportDto : orderImportRootDto.getOrderImportDtos()){

            Employee employee =
                    this.employeeRepository.findByName(orderImportDto.getEmployee()).orElse(null);

            if(employee == null || !this.validationUtil.isValid(orderImportDto)){
                System.out.println("error");
                continue;
            }

            Order order = new Order();
            order.setCustomer(orderImportDto.getCustomer());
            order.setDate(LocalDateTime.parse(orderImportDto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            order.setType(orderImportDto.getType());
            order.setEmployee(employee);
            order.setTotalPrice(BigDecimal.TEN);

            List<OrderItem> items = new ArrayList<>();
            Boolean hasInvalidItem = false;
            for(ItemImportXmlDto itemImportXmlDto : orderImportDto.getItems().getItemImportXmlDto()){

                Item item = this.itemRepository
                        .findByName(itemImportXmlDto.getName())
                        .orElse(null);

                if(item == null){
                    hasInvalidItem = true;
                    break;
                }

                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setItem(item);
                orderItem.setQuantity(itemImportXmlDto.getQuantity());

                items.add(orderItem);

            }

            if(!hasInvalidItem) {
                this.orderRepository.saveAndFlush(order);
                for (OrderItem orderItem : items) {
                    this.orderItemIRepository.saveAndFlush(orderItem);
                }
            }
        }
    }
}
