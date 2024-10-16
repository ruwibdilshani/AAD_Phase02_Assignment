package lk.ijse.gdse.aad.posusingspring.util;


import lk.ijse.gdse.aad.posusingspring.dto.CustomerDto;
import lk.ijse.gdse.aad.posusingspring.dto.ItemDto;
import lk.ijse.gdse.aad.posusingspring.dto.OrderDetailsDto;
import lk.ijse.gdse.aad.posusingspring.dto.OrderDto;
import lk.ijse.gdse.aad.posusingspring.entity.Customer;
import lk.ijse.gdse.aad.posusingspring.entity.Item;
import lk.ijse.gdse.aad.posusingspring.entity.OrderDetails;
import lk.ijse.gdse.aad.posusingspring.entity.Orders;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public Customer convertToCusEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }

    public CustomerDto convertToCusDto(Customer customer){
        return modelMapper.map(customer, CustomerDto.class);
    }

    public List<CustomerDto> convertToCusDtoList(List<Customer> customerList){
        return modelMapper.map(customerList,new TypeToken<List<CustomerDto>>(){}.getType()); //entities,List.class
    }

    public Item convertTItemEntity(ItemDto itemDto){
        return modelMapper.map(itemDto, Item.class);
    }

    public ItemDto convertToItemDto(Item item){
        return modelMapper.map(item, ItemDto.class);
    }

    public List<ItemDto> convertToItemDtoList(List<Item> itemList){
        return modelMapper.map(itemList, new TypeToken<List<ItemDto>>(){}.getType());
    }

    public Orders convertToOrderEntity(OrderDto dto){
        return modelMapper.map(dto, Orders.class);
    }


    public OrderDetails convertToOrderDetailEntity(OrderDetailsDto dto){
        return modelMapper.map(dto, OrderDetails.class);
    }
}
