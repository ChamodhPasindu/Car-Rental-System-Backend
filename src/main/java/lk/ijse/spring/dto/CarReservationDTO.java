package lk.ijse.spring.dto;

import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CarReservationDTO {
    private String reserve_id;
    private Date reserve_date;
    private Date pick_up_date;
    private Date return_date;
    private Time pick_up_time;
    private String pick_up_and_return_venue;
    private int no_of_days;
    private String reservation_status;

    private CustomerDTO customerDTO;

    private CarDTO carDTO;

    private DriverDTO driverDTO;
}
