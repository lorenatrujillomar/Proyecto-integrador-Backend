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
public class DomicilioRequestDto {
    @NotBlank(message = "La calle no puede estar vacío")
    private String calle;
    @NotBlank(message = "El número no puede estar vacío")
    private Integer numero;
    @NotBlank(message = "La localidad no puede estar vacío")
    private String localidad;
    @NotBlank(message = "La provincia no puede estar vacío")
    private String provincia;
}
