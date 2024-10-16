package lk.ijse.gdse.aad.posusingspring.controller;


import lk.ijse.gdse.aad.posusingspring.customObj.CustomerResponse;
import lk.ijse.gdse.aad.posusingspring.dto.CustomerDto;
import lk.ijse.gdse.aad.posusingspring.exception.CustomerNotFoundException;
import lk.ijse.gdse.aad.posusingspring.exception.DataPersistFailedException;
import lk.ijse.gdse.aad.posusingspring.service.CustomerService;
import lk.ijse.gdse.aad.posusingspring.util.AppUtil;
import lk.ijse.gdse.aad.posusingspring.util.Validation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDto customerDto){

        customerDto.setCustomerId(AppUtil.createCusId());
        String validation = Validation.validationCustomer(customerDto);
        logger.info("Request to save customer {}", customerDto);
        if (validation.equals("Invalid")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            customerService.saveCustomer(customerDto);
            logger.info("Successfully saved customer: {}", customerDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{customerId}")
    public CustomerResponse getCustomer(@PathVariable ("customerId") String customerId){
        logger.info("Request to get customer {}", customerId);
        return customerService.getCustomer(customerId);
    }

    @GetMapping
    public List<CustomerDto> getAllCustomers(){
        logger.info("Request to get all customers");
        return customerService.getAllCustomers();
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable ("customerId") String customerId, @RequestBody CustomerDto customerDto){

//        String validation = Validation.validationCustomer(customerDto);
//        if (validation.equals("Invalid")) {
//
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        try {
            customerService.updateCustomer(customerId, customerDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistFailedException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable ("customerId") String customerId){
        logger.info("Request to delete customer {}", customerId);
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}