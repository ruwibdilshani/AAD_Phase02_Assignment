package lk.ijse.gdse.aad.posusingspring.dto;


import lk.ijse.gdse.aad.posusingspring.customObj.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto implements Serializable, OrderResponse {
    private String orderId;
    private String customerId;
    private List<OrderDetailsDto> orderDetails;
    private LocalDateTime orderDateTime;
    private double total;
}
