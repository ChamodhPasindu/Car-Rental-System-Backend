package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Admin;
import lk.ijse.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepo extends JpaRepository<Admin,String> {

    @Query(value = "select * from admin where admin_name=?1 and password=?2",nativeQuery = true)
    Admin checkAdminLogIn(String name, String password);
}
