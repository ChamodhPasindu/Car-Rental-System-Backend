package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer,String> {

    @Query(value = "select * from customer where user_name=?1 and password=?2",nativeQuery = true)
    Customer checkCustomerLogIn(String name,String password);

}
