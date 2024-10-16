package lk.ijse.gdse.aad.posusingspring.util;

import lk.ijse.gdse.aad.posusingspring.dto.CustomerDto;
import lk.ijse.gdse.aad.posusingspring.dto.ItemDto;

public class Validation {

    public static String validationCustomer(CustomerDto customerDto){
        if (customerDto.getCustomerId() == null || customerDto.getCustomerId().trim().isEmpty() ||
                !customerDto.getCustomerId().matches("^[Cc].*")) {
            return "Invalid";
        } else if (customerDto.getCustomerName() == null || customerDto.getCustomerName().trim().isEmpty() ||
                !customerDto.getCustomerName().matches("[A-Za-z ]+")) {
            return "Invalid";
        } else if (customerDto.getCustomerAddress() == null || customerDto.getCustomerAddress().trim().isEmpty() ||
                !customerDto.getCustomerAddress().matches("[A-Za-z0-9 ,]+")) {
            return "Invalid";
        } else if (customerDto.getCustomerSalary() == 0 || customerDto.getCustomerSalary() <= 0) {
            return "Invalid";
        } else {
            return "Valid";
        }
    }


    public static String validationItem(ItemDto itemDto){
        if (itemDto.getItemCode() == null || itemDto.getItemCode().trim().isEmpty() ||
                !itemDto.getItemCode().matches("^[Ii].*")) {
            return "Invalid Item Code";
        } else if (itemDto.getItemName() == null || itemDto.getItemName().trim().isEmpty() ||
                !itemDto.getItemName().matches("[A-Za-z ]+")) {
            return "Invalid Item Name";
        } else if (itemDto.getItemQty() == 0 || itemDto.getItemQty() <= 0) {
            return "Invalid Item Quantity";
        } else if (itemDto.getItemPrice() == 0 || itemDto.getItemPrice() <= 0) {
            return "Invalid Item Price";
        } else {
            return "Valid";
        }
    }

}
