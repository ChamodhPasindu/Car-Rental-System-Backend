package lk.ijse.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DriverScheduleDTO {
    private int schedule_id;

    @JsonFormat(pattern = "HH:mm:ss")
    private Time start_time;
    private Date start_date;
    private Date end_date;

    private DriverDTO driver;

    private CarReservationDTO carReservation;

    public DriverScheduleDTO(Time start_time, Date start_date, Date end_date, DriverDTO driver, CarReservationDTO carReservation) {
        this.start_time = start_time;
        this.start_date = start_date;
        this.end_date = end_date;
        this.driver = driver;
        this.carReservation = carReservation;
    }
}
