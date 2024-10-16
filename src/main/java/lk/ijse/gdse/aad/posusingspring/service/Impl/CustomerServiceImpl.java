package lk.ijse.gdse.aad.posusingspring.service.Impl;

import jakarta.transaction.Transactional;

import lk.ijse.gdse.aad.posusingspring.customObj.Impl.CustomerErrorResponse;
import lk.ijse.gdse.aad.posusingspring.customObj.CustomerResponse;
import lk.ijse.gdse.aad.posusingspring.dao.CustomerDao;
import lk.ijse.gdse.aad.posusingspring.dto.CustomerDto;
import lk.ijse.gdse.aad.posusingspring.entity.Customer;
import lk.ijse.gdse.aad.posusingspring.exception.CustomerNotFoundException;
import lk.ijse.gdse.aad.posusingspring.exception.DataPersistFailedException;
import lk.ijse.gdse.aad.posusingspring.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerDao customerDao;
    @Autowired
    private final Mapping mapping;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        if (customerDao.existsById(customerDto.getCustomerId())) {
            throw new DataPersistFailedException("This customer ID already exists!");
        }else{
            Customer savedCustomer = customerDao.save(mapping.convertToCusEntity(customerDto));
            if (savedCustomer == null && savedCustomer.getCustomerId() == null) {
                throw new DataPersistFailedException("Can't save the customer!");
            }
        }

    }

    @Override
    public CustomerResponse getCustomer(String customerId) {
        if (customerDao.existsById(customerId)) {
            return mapping.convertToCusDto(customerDao.getCustomerByCustomerId(customerId));
        } else {
            return new CustomerErrorResponse(0, "Customer Not Found!");
        }

    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return mapping.convertToCusDtoList(customerDao.findAll());
    }

    @Override
    public void updateCustomer(String customerId, CustomerDto incomeCustomerDto) {
        Optional<Customer> tmpCustomer = customerDao.findById(customerId);
        if (tmpCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer Not Found!");
        }else {
            Customer customer = tmpCustomer.get();
            tmpCustomer.get().setCustomerName(incomeCustomerDto.getCustomerName());
            tmpCustomer.get().setCustomerAddress(incomeCustomerDto.getCustomerAddress());
            tmpCustomer.get().setCustomerSalary(incomeCustomerDto.getCustomerSalary());

            customerDao.save(customer);
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        Optional<Customer> existingCustomer = customerDao.findById(customerId);
        if (!existingCustomer.isPresent()) {
            throw new CustomerNotFoundException("Customer Not Found!");
        } else {
            customerDao.deleteById(customerId);
        }
    }
}
