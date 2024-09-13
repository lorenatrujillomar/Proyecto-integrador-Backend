package proyecto.integrador.clinica.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OdontologoModificarDto {
    private Integer id;
    @NotBlank(message = "El número de matricula no puede estar vacío")
    private String numeromatricula;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
}
