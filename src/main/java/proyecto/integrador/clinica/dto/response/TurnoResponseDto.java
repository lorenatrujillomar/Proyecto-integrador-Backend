package proyecto.integrador.clinica.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyecto.integrador.clinica.utils.GsonProviter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoResponseDto {
    private Integer id;
    // datos del paciente
    private PacienteResponseDto pacienteResponseDto;
    // datos del odontologo
    private OdontologoResponseDto odontologoResponseDto;
    private String fecha;

    @Override
    public String toString(){
        return GsonProviter.getGson().toJson(this);
    }
}
