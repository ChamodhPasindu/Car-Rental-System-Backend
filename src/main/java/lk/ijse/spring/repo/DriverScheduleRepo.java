package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.entity.DriverSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface DriverScheduleRepo extends JpaRepository<DriverSchedule,String> {

    @Query(value = "SELECT * FROM driver_schedule WHERE start_date BETWEEN ?1 AND ?2 OR end_date BETWEEN ?1 AND ?2",nativeQuery = true)
    List<DriverSchedule> getDriverSchedulesByDate(String start_date,String end_date);


}
