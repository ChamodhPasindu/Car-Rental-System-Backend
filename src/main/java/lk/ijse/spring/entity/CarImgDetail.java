package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "car_img_detail")
public class CarImgDetail {

    @Id
    @Column(name = "img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String image_1;
    private String image_2;
    private String image_3;
    private String image_4;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_no")
    private Car car;
}
