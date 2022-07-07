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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int img_id;

    private String image_1;
    private String image_2;
    private String image_3;
    private String image_4;


}
