package beyond.ordersystem.ordering.service;

import beyond.ordersystem.member.domain.Member;
import beyond.ordersystem.member.repository.MemberRepository;
import beyond.ordersystem.ordering.domain.OrderDetail;
import beyond.ordersystem.ordering.domain.Ordering;
import beyond.ordersystem.ordering.dto.OrderingSaveReqDto;
import beyond.ordersystem.ordering.dto.OrderingResDto;
import beyond.ordersystem.ordering.repository.OrderingRepository;
import beyond.ordersystem.product.domain.Product;
import beyond.ordersystem.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderingService {
    private final OrderingRepository orderingRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public OrderingService(OrderingRepository orderingRepository, MemberRepository memberRepository, ProductRepository productRepository) {
        this.orderingRepository = orderingRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    public Ordering createOrdering(OrderingSaveReqDto dto){
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(()->new EntityNotFoundException("member is not found"));

        Ordering ordering = Ordering.builder()
                .member(member)
                .build();

        for (OrderingSaveReqDto.OrderDetailDto orderDetailDto : dto.getOrderDetailDtoList()){
            Product product = productRepository.findById(orderDetailDto.getProductId()).orElseThrow(()->new EntityNotFoundException("product is not found"));
            OrderDetail orderDetail = OrderDetail.builder()
                   .product(product)
                    .ordering(ordering)
                   .quantity(orderDetailDto.getProductCount())
                   .build();
            ordering.getOrderDetails().add(orderDetail);


//            부비부비 밤밤 바비바비 붐붐
        }

        Ordering savedOrdering = orderingRepository.save(ordering);
        return savedOrdering;
    }

//    public Page<OrderingResDto> orderingList(Pageable pageable){
//        Page<Ordering> orderings = orderingRepository.findAll(pageable);
//        return orderings.map(Ordering::fromEntity);
//    }

}
