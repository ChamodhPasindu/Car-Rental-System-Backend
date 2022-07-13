package lk.easycar.spring.service;

import lk.easycar.spring.dto.CustomerDTO;
import lk.easycar.spring.dto.UserDTO;
import lk.easycar.spring.entity.Customer;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);

    String updateCustomer(CustomerDTO customerDTO);

    void changeCustomerUsernameAndPassword(UserDTO userDTO);

    void deleteCustomer(String id);

    CustomerDTO getCustomerDetail(String id);

    CustomerDTO checkCustomerLogIn(String user_name,String password);

    List<CustomerDTO>getAllCustomerDetail();

    List<CustomerDTO> getTodayRegisteredCustomers();



}
