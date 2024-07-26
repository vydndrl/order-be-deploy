package beyond.ordersystem.ordering.controller;

import beyond.ordersystem.common.dto.CommonResDto;
import beyond.ordersystem.ordering.domain.Ordering;
import beyond.ordersystem.ordering.dto.OrderingListResDto;
import beyond.ordersystem.ordering.dto.OrderingSaveReqDto;
import beyond.ordersystem.ordering.service.OrderingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
public class OrderingController {
    private final OrderingService orderingService;

    public OrderingController(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    @PostMapping("/ordering/create")
    public ResponseEntity<?> createOrdering(@RequestBody OrderingSaveReqDto dto){
        Ordering ordering = orderingService.orderingCreate(dto);
        return new ResponseEntity<>(new CommonResDto(HttpStatus.CREATED, "주문완료",  ordering.getId()), HttpStatus.CREATED);
        // ordering.getId() 하는 이유 : 엔티티 그대로 리턴하면 순환참조에 빠질 수 있음
    }

    @GetMapping("/ordering/list")
    public ResponseEntity<?> orderingList(){
        List<OrderingListResDto> orderList = orderingService.orderingList();
        return new ResponseEntity<>(new CommonResDto(HttpStatus.OK, "OK", orderList), HttpStatus.OK);
    }


}
