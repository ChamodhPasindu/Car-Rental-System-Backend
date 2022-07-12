package lk.easycar.spring.repo;

import lk.easycar.spring.entity.DriverSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DriverScheduleRepo extends JpaRepository<DriverSchedule,String> {

    @Query(value = "SELECT * FROM driver_schedule WHERE start_date BETWEEN ?1 AND ?2 OR end_date BETWEEN ?1 AND ?2",nativeQuery = true)
    List<DriverSchedule> getDriverSchedulesByDate(String start_date,String end_date);

    @Query(value = "SELECT * FROM driver_schedule WHERE reserve_id=?1 ",nativeQuery = true)
    DriverSchedule getDriverSchedulesByReservationId(String reserve_id);

    @Query(value = "SELECT * FROM driver_schedule WHERE (driver_nic=?1) AND (start_date BETWEEN ?2 AND ?3 OR end_date BETWEEN ?2 AND ?3)",nativeQuery = true)
    List<DriverSchedule> getDriverWeeklyOrMonthlyScheduleByDate(String driver_id, LocalDate first_date,LocalDate last_date);

    //SELECT * FROM driver_schedule WHERE (driver_nic='002') AND start_date BETWEEN '2022-07-01' AND '2022-07-31' OR end_date BETWEEN '2022-07-01' AND '2022-07-31'
}
