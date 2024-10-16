package lk.ijse.gdse.aad.posusingspring.service;


import lk.ijse.gdse.aad.posusingspring.dto.OrderDetailsDto;
import lk.ijse.gdse.aad.posusingspring.dto.OrderDto;

public interface OrderService {
    String saveOrder(OrderDto orderDTO);
    //boolean  updateItemQty(OrderDetailsDto orderDetailsDto);
}
