package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);

    void updateCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerDetail(String id);

    CustomerDTO checkCustomerLogIn(String user_name,String password);

    List<CustomerDTO>getAllCustomerDetail();


}
