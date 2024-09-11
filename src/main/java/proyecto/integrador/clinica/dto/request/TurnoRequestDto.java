package proyecto.integrador.clinica.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyecto.integrador.clinica.utils.GsonProviter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoRequestDto {
    private Integer paciente_id;
    private Integer odontologo_id;
    private String  fecha;
    @Override
    public String toString(){
        return GsonProviter.getGson().toJson(this);
    }
}
