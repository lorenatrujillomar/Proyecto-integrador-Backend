package proyecto.integrador.clinica.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DomicilioRequestDto {
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;
}
