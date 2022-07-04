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
@Entity(name = "driver_schedule")
public class DriverSchedule {
    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Time start_time;
    private Date start_date;
    private Date end_date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="driver_nic")
    private Driver driver;
}