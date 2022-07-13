package lk.easycar.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "admin")
public class Admin {
    @Id
    private String admin_id;

    @Column(name = "admin_name")
    private String name;
    private String password;
}
