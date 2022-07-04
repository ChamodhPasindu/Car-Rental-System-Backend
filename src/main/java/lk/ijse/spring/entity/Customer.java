package lk.ijse.spring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "customer")
public class Customer {
    @Id
    @Column(name = "nic")
    private String id;
    private String user_name;
    private String password;

    @Column(name = "customer_name")
    private String name;
    private String license_no;
    private String address;
    private String mobile;
    private String email;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<CarReservation>reservations;
}
