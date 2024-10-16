package lk.ijse.gdse.aad.posusingspring.service;


import lk.ijse.gdse.aad.posusingspring.customObj.ItemResponse;
import lk.ijse.gdse.aad.posusingspring.dto.ItemDto;

import java.util.List;

public interface ItemService {
     void saveItem(ItemDto itemDto);
     void updateItem(String itemCode, ItemDto itemDto);
     ItemResponse getItem(String itemCode);
     List<ItemDto> getAllItems();
     void deleteItem(String itemCode);
}
