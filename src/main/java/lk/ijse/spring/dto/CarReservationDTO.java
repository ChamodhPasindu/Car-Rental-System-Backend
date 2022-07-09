package lk.ijse.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
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
    @JsonFormat(pattern = "HH:mm:ss")
    private Time pick_up_time;
    private String pick_up_and_return_venue;
    private int no_of_days;
    private BigDecimal waiver_payment;
    private String bank_slip_img;
    private String reservation_status;
    private String driver_status;

    private CustomerDTO customer;

    private CarDTO car;

}
