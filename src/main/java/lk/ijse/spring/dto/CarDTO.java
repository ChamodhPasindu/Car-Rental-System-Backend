package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CarDTO {

    private String registration_id;
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
}
