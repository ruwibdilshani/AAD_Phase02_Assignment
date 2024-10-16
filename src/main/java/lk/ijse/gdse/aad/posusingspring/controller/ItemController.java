package lk.ijse.gdse.aad.posusingspring.controller;

import lk.ijse.gdse.aad.posusingspring.customObj.ItemResponse;
import lk.ijse.gdse.aad.posusingspring.dto.ItemDto;
import lk.ijse.gdse.aad.posusingspring.exception.DataPersistFailedException;
import lk.ijse.gdse.aad.posusingspring.exception.ItemNotFoundException;
import lk.ijse.gdse.aad.posusingspring.service.ItemService;
import lk.ijse.gdse.aad.posusingspring.util.AppUtil;
import lk.ijse.gdse.aad.posusingspring.util.Validation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

    Logger logger = LoggerFactory.getLogger(ItemController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDto itemDto){
        itemDto.setItemCode(AppUtil.createItemCode());
        String validation = Validation.validationItem(itemDto);
        logger.info("Request to save customer {}", itemDto);
        if (validation.equals("Invalid")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
            try {
                itemService.saveItem(itemDto);
                logger.info("Successfully saved item: {}", itemDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


    @PatchMapping("/{itemCode}")
    public ResponseEntity<Void> updateItem(@PathVariable ("itemCode") String itemCode, @RequestBody ItemDto itemDto){
        logger.info("Request to update item {}", itemDto);
        String validation = Validation.validationItem(itemDto);
        if (validation.equals("Invalid")) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            itemService.updateItem(itemCode, itemDto);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{itemCode}")
    public ItemResponse getItem(@PathVariable ("itemCode") String itemCode){
        logger.info("Request to get item {}", itemCode);
        return itemService.getItem(itemCode);
    }

    @GetMapping
    public List<ItemDto> getAllItems(){
        logger.info("Request to get all items");
        return itemService.getAllItems();
    }

    @DeleteMapping("/{itemCode}")
    public ResponseEntity<Void> deleteItem(@PathVariable ("itemCode") String itemCode) {
        logger.info("Request to delete item {}", itemCode);
        try {
            itemService.deleteItem(itemCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
