package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);

    void updateCustomer(CustomerDTO customerDTO);

    CustomerDTO checkCustomerLogIn(String id,String password);


}
