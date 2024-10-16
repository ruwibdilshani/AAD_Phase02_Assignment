package lk.ijse.gdse.aad.posusingspring.service;


import lk.ijse.gdse.aad.posusingspring.customObj.CustomerResponse;
import lk.ijse.gdse.aad.posusingspring.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
     void saveCustomer(CustomerDto customerDto);
     CustomerResponse getCustomer(String customerId);
     List<CustomerDto> getAllCustomers();
     void updateCustomer(String customerId, CustomerDto customerDto);
     void deleteCustomer(String customerId);
}
