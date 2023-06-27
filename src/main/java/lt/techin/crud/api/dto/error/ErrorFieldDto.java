package lt.techin.crud.api.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorFieldDto {
    private String name;
    private String error;
    private String rejectedValue;

}
