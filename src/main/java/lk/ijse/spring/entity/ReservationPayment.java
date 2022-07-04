package lk.ijse.spring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "reservation_payment")
public class ReservationPayment {
    @Id
    @Column(name = "bill_id")
    private String id;

    private Date pay_date;
    private BigDecimal rental_fee;
    private BigDecimal driver_fee;
    private BigDecimal waiver_payment;
    private BigDecimal damage_cost;
    private BigDecimal return_cost;
    private BigDecimal total_payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="reserve_id")
    private CarReservation carReservation;
}
