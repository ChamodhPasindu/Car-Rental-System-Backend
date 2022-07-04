package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "car")
public class Car {
    @Id
    @Column(name = "registration_no")
    private String id;

    private String brand;
    private String type;
    private String transmission;
    private String color;
    private int no_of_passengers;
    private int mileage;
    private String fuel_type;
    private String daily_rate;
    private String monthly_rate;
    private String free_km_for_day;
    private String free_km_for_month;
    private BigDecimal price_for_extra_km;
    private String status;

    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    private List<CarReservation> reservations;
}
