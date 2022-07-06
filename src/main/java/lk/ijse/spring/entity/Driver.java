package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "driver")
public class Driver {
    @Id
    @Column(name = "nic")
    private String id;

    private String driver_name;
    private String address;
    private String license_no;
    private String mobile;
    private Date join_date;

    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    private List<CarReservation>carReservations;
}
