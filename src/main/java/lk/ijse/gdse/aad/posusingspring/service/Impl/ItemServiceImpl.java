package lk.ijse.gdse.aad.posusingspring.service.Impl;

import jakarta.transaction.Transactional;

import lk.ijse.gdse.aad.posusingspring.customObj.Impl.ItemErrorResponse;
import lk.ijse.gdse.aad.posusingspring.customObj.ItemResponse;
import lk.ijse.gdse.aad.posusingspring.dao.ItemDao;
import lk.ijse.gdse.aad.posusingspring.dto.ItemDto;
import lk.ijse.gdse.aad.posusingspring.entity.Item;
import lk.ijse.gdse.aad.posusingspring.exception.DataPersistFailedException;
import lk.ijse.gdse.aad.posusingspring.exception.ItemNotFoundException;
import lk.ijse.gdse.aad.posusingspring.service.ItemService;
import lk.ijse.gdse.aad.posusingspring.util.AppUtil;
import lk.ijse.gdse.aad.posusingspring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    @Autowired
    private final ItemDao itemDao;
    @Autowired
    private final  Mapping mapping;

    @Override
    public void saveItem(ItemDto itemDto) {
        if (itemDao.existsById(itemDto.getItemCode())) {
            throw new DataPersistFailedException("This Item Code already exists!");
        }else {

            Item savedItem = itemDao.save(mapping.convertTItemEntity(itemDto));
            if (savedItem == null && savedItem.getItemCode() == null) {
                throw new DataPersistFailedException("Can't save the Item!");
            }
        }

    }

    @Override
    public void updateItem(String itemCode, ItemDto incomingItem) {
        System.out.println("Impl"+ itemCode);
        Optional<Item> existItem = itemDao.findById(itemCode);
        if (existItem == null) {
            throw new ItemNotFoundException("Item not found!");
        } else {
            existItem.get().setItemName(incomingItem.getItemName());
            existItem.get().setItemQty(incomingItem.getItemQty());
            existItem.get().setItemPrice(incomingItem.getItemPrice());
        }
    }

    @Override
    public ItemResponse getItem(String itemCode) {
        if (itemDao.existsById(itemCode)) {
            return mapping.convertToItemDto(itemDao.getItemByItemCode(itemCode));
        } else {
            return new ItemErrorResponse(0, "Item Not Found!");
        }
    }

    @Override
    public List<ItemDto> getAllItems() {
        return mapping.convertToItemDtoList(itemDao.findAll());
    }

    @Override
    public void deleteItem(String itemCode){
        Optional<Item> existsItem = itemDao.findById(itemCode);
        if (!existsItem.isPresent()) {
            throw new ItemNotFoundException("Item not found!");
        } else {
            itemDao.deleteById(itemCode);
        }
    }
}
