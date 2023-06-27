package lt.techin.crud.api.dto.technic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicDto {

    private String name;
    private String surname;
    private String speciality;
    private String city;
    private Double review;
}
