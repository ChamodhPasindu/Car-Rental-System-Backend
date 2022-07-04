package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "car_reservation")
public class CarReservation {
    @Id
    @Column(name = "reserve_id")
    private String id;

    private Date reserve_date;
    private Date pick_up_date;
    private Date return_date;
    private Time pick_up_time;
    private String pick_up_and_return_venue;
    private int no_of_days;
    private String reservation_status;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name = "customer_nic")
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name = "registration_no")
    private Car car;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name = "driver_id")
    private Driver driver;


}
