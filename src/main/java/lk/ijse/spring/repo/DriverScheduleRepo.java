package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.entity.DriverSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface DriverScheduleRepo extends JpaRepository<DriverSchedule,String> {

    @Query(value = "SELECT * FROM driver_schedule WHERE start_date BETWEEN ?1 AND ?2 OR end_date BETWEEN ?1 AND ?2",nativeQuery = true)
    List<DriverSchedule> getDriverSchedulesByDate(String start_date,String end_date);

    @Query(value = "SELECT * FROM driver_schedule WHERE reserve_id=?1 ",nativeQuery = true)
    DriverSchedule getDriverSchedulesByReservationId(String reserve_id);

    @Query(value = "SELECT * FROM driver_schedule WHERE (driver_nic=?1) AND (start_date BETWEEN (SELECT CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY) AND (SELECT CURDATE()- INTERVAL WEEKDAY(CURDATE())-6 DAY) OR end_date BETWEEN (SELECT CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY) AND (SELECT CURDATE()- INTERVAL WEEKDAY(CURDATE())-6 DAY))\n",nativeQuery = true)
    List<DriverSchedule> getDriverWeeklyScheduleByDate(String driver_id, LocalDate first_date,LocalDate last_date);
/*
   SELECT DATE_ADD(DD,-(DAY(CURDATE() -1)), CURDATE()) AS FirstDate
   SELECT EOMONTH (CURDATE(), 0) AS LastDayOfCurrentMonth
   */
}
