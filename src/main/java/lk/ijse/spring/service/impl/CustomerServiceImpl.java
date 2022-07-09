package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        if (!customerRepo.existsById(customerDTO.getNic())) {
            customerRepo.save(mapper.map(customerDTO, Customer.class));
        } else {
            throw new RuntimeException("Customer Already Registered..!");
        }
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getNic())) {
            customerRepo.save(mapper.map(customerDTO, Customer.class));
        } else {
            throw new RuntimeException("Something Wrong,Cant Update Your Details.Please Contact Admin");
        }
    }

    @Override
    public void deleteCustomer(String id) {
        if (customerRepo.existsById(id)){
            customerRepo.deleteById(id);
        }else {
            throw new RuntimeException("Something Wrong,Cant Delete Your Details.Please Contact Admin");
        }
    }

    @Override
    public CustomerDTO getCustomerDetail(String id) {
        if (customerRepo.existsById(id)) {
            return mapper.map(customerRepo.findById(id).get(), CustomerDTO.class);
        } else {
            throw new RuntimeException("Something Wrong,Cant Get Your Details.Please Contact Admin");
        }
    }

    @Override
    public CustomerDTO checkCustomerLogIn(String user_name, String password) {
        Customer customer = customerRepo.checkCustomerLogIn(user_name, password);
        if (!(customer == null)) {
            return mapper.map(customer, CustomerDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomerDetail() {
        return mapper.map(customerRepo.findAll(), new TypeToken<List<CustomerDTO>>() {
        }.getType());
    }

    @Override
    public List<CustomerDTO> getTodayRegisteredCustomers() {
        return mapper.map(customerRepo.getTodayRegisteredCustomers(),new TypeToken<List<CustomerDTO>>(){
        }.getType());
    }
}
