package proyecto.integrador.clinica.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponseDto
{
    private Integer id;
    private String apellido;
    private String nombre;
    private String dni;
    private String fechaIngreso;
    private DomicilioResponseDto domicilio;
}
