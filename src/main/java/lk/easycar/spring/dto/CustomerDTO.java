package lk.easycar.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerDTO {
    private String nic;
    private String user_name;
    private String password;
    private String customer_name;
    private String license_no;
    private String license_img;
    private String nic_img;
    private String address;
    private String mobile;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Kolkata")
    private Date register_date;

}
