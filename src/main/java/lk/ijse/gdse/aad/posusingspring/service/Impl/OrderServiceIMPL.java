package lk.ijse.gdse.aad.posusingspring.service.Impl;


import lk.ijse.gdse.aad.posusingspring.dao.ItemDao;
import lk.ijse.gdse.aad.posusingspring.dao.OrdersDao;
import lk.ijse.gdse.aad.posusingspring.dto.OrderDetailsDto;
import lk.ijse.gdse.aad.posusingspring.dto.OrderDto;
import lk.ijse.gdse.aad.posusingspring.entity.Item;
import lk.ijse.gdse.aad.posusingspring.entity.OrderDetails;
import lk.ijse.gdse.aad.posusingspring.entity.Orders;
import lk.ijse.gdse.aad.posusingspring.exception.DataPersistFailedException;
import lk.ijse.gdse.aad.posusingspring.exception.ItemNotFoundException;
import lk.ijse.gdse.aad.posusingspring.service.OrderService;
import lk.ijse.gdse.aad.posusingspring.util.AppUtil;
import lk.ijse.gdse.aad.posusingspring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private final Mapping mapping;

    @Autowired
    private final OrdersDao ordersDao;

    @Autowired
   private final ItemDao itemDao;

    @Override
    public String saveOrder(OrderDto orderDTO) {

        orderDTO.setOrderId(AppUtil.createOrderId());
        orderDTO.setOrderDateTime(AppUtil.getCurrentDateTime());
        orderDTO.setTotal(orderDTO.getOrderDetails().stream().mapToDouble(detail -> detail.getQty() * detail.getUnitPrice()).sum());

        Orders orderEntity = mapping.convertToOrderEntity(orderDTO);

        List<OrderDetails> orderDetailEntities = orderDTO.getOrderDetails().stream().map(detail -> {

                    OrderDetails orderDetailEntity = mapping.convertToOrderDetailEntity(detail);
                    orderDetailEntity.setOrderDetailsId(AppUtil.createOrderDetailsId());
                    orderDetailEntity.setDescription("Payed");
                    orderDetailEntity.setOrder(orderEntity);
                    return orderDetailEntity;
                })
                .collect(Collectors.toList());

        orderEntity.setOrderDetails(orderDetailEntities);
        boolean allItemsUpdated = orderDTO.getOrderDetails().stream().allMatch(this::updateItemQty);

        if (allItemsUpdated) {
            ordersDao.save(orderEntity);
            return "Order placed successfully";
        } else {
            throw new DataPersistFailedException("place order failed");
        }



    }

    private boolean updateItemQty(OrderDetailsDto orderDetailsDto) {
        Item item = itemDao.findById(orderDetailsDto.getItemCode()).orElse(null);
        if (item == null) {
            throw new ItemNotFoundException("Item not found");
        }

        if (item.getItemQty() < orderDetailsDto.getQty()) {
            throw new ItemNotFoundException("Item qty not enough");
        }

        item.setItemQty(item.getItemQty() - orderDetailsDto.getQty());
        itemDao.save(item);
        return true;
    }
}