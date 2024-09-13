package proyecto.integrador.clinica.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OdontologoRequestDto {
    private Integer id;
    @Size(min = 8, max = 15, message = "El número de matricula debe tener entre 8 y 15 caracteres")
    @NotBlank(message = "El número de matricula no puede estar vacío")
    private String numeromatricula;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
}
