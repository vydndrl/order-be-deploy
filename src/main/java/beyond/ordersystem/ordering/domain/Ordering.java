package beyond.ordersystem.ordering.domain;

import beyond.ordersystem.ordering.dto.OrderingResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ordering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orderDetail", cascade = CascadeType.ALL)
    @JoinColumn(name = "orderDetail_id")
    private OrderDetail orderDetail;

    public OrderingResDto fromEntity(){
        return OrderingResDto.builder()

                .build();
    }
}
