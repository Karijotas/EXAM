package lt.techin.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "car_shop_techics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarShopTechnics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_shop_id")
    private CarShop carShop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technic_id")

    private Technic technic;

}
