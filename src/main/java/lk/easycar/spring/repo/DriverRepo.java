package lk.easycar.spring.repo;

import lk.easycar.spring.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface DriverRepo extends JpaRepository<Driver,String> {

    @Query(value = "SELECT * FROM driver WHERE nic NOT IN (SELECT DISTINCT driver_nic FROM driver_schedule WHERE start_date BETWEEN ?1 AND ?2 OR end_date BETWEEN ?1 AND ?2)ORDER BY RAND() DESC limit 1" ,nativeQuery = true)
    Driver selectDriverForReservation(Date pick_date, Date return_date);

    @Query(value = "SELECT * FROM driver WHERE nic NOT IN(SELECT DISTINCT driver_nic FROM driver_schedule WHERE current_date() BETWEEN start_date AND end_date) ",nativeQuery = true)
    Driver getTodayAvailableDrivers();

    @Query(value = "SELECT * FROM driver WHERE nic IN(SELECT DISTINCT driver_nic FROM driver_schedule WHERE current_date() BETWEEN start_date AND end_date) ",nativeQuery = true)
    Driver getTodayOccupiedDrivers();

}
