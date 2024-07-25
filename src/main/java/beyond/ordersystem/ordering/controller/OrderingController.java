package beyond.ordersystem.ordering.controller;

import beyond.ordersystem.ordering.service.OrderingService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderingController {
    private final OrderingService orderingService;

    public OrderingController(OrderingService orderingService) {
        this.orderingService = orderingService;
    }
}
