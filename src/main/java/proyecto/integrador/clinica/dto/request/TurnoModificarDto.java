package proyecto.integrador.clinica.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoModificarDto {
    private Integer id;
    @NotBlank(message = "El codigo de paciente no debe ser nulo")
    private Integer paciente_id;
    @NotBlank(message = "El codigo de odontologo no debe ser nulo")
    private Integer odontologo_id;
    @NotNull(message = "La fecha no debe estar vacia")
    private String  fecha;
}
