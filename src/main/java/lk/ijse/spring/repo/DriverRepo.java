package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface DriverRepo extends JpaRepository<Driver,String> {

    @Query(value = "SELECT * FROM driver WHERE nic NOT IN (SELECT DISTINCT driver_nic FROM driver_schedule WHERE start_date BETWEEN ?1 AND ?2 OR end_date BETWEEN ?1 AND ?2)ORDER BY RAND() DESC limit 1" ,nativeQuery = true)
    Driver selectDriverForReservation(Date pick_date, Date return_date);
}
