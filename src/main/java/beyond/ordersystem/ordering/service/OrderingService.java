package beyond.ordersystem.ordering.service;

import beyond.ordersystem.ordering.domain.Ordering;
import beyond.ordersystem.ordering.dto.OrderingReqDto;
import beyond.ordersystem.ordering.dto.OrderingResDto;
import beyond.ordersystem.ordering.repository.OrderDetailRepository;
import beyond.ordersystem.ordering.repository.OrderingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderingService {
    private final OrderingRepository orderingRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderingService(OrderingRepository orderingRepository, OrderDetailRepository orderDetailRepository) {
        this.orderingRepository = orderingRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Ordering createOrdering(OrderingReqDto dto){
        Ordering ordering = dto.toEntity();
        return orderingRepository.save(ordering);
    }

    public Page<OrderingResDto> orderingList(Pageable pageable){
        Page<Ordering> orderings = orderingRepository.findAll(pageable);
        return orderings.map(Ordering::fromEntity);

    }



}
