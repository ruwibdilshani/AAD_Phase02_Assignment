package lk.ijse.gdse.aad.posusingspring.dto;


import lk.ijse.gdse.aad.posusingspring.customObj.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailsDto implements Serializable, OrderResponse {
    private String orderId;
    private String itemCode;
    private int qty;
    private double unitPrice;
    private double total;
}
