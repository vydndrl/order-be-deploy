package beyond.ordersystem.ordering.dto;

import beyond.ordersystem.ordering.domain.Ordering;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderingReqDto {
    private Long memberId;
    private Long productId;
    private Integer productCount;


    public Ordering toEntity(){
        return Ordering.builder()
                .id(this.memberId)
                .
                .build();
    }
}
