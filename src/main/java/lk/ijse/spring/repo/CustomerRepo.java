package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer,String> {

    @Query(value = "SELECT * FROM customer WHERE user_name=?1 AND password=?2",nativeQuery = true)
    Customer checkCustomerLogIn(String name,String password);

}
