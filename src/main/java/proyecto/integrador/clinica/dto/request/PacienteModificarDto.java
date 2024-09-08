package proyecto.integrador.clinica.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteModificarDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private String fechaIngreso;
    private Integer domicilioId;
}
