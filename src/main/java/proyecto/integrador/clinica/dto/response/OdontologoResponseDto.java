package proyecto.integrador.clinica.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OdontologoResponseDto {
    private Integer id;
    private String numeromatricula;
    private String apellido;
    private String nombre;
}
