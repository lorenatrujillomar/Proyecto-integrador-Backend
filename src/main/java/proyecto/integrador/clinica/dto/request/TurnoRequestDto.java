package proyecto.integrador.clinica.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyecto.integrador.clinica.utils.GsonProviter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoRequestDto {
    @NotNull(message = "El código de paciente no debe ser nulo")
    private Integer paciente_id;
    @NotNull(message = "El código de odontologo no debe ser nulo")
    private Integer odontologo_id;
    @NotNull(message = "La fecha no debe estar vacia")
    private LocalDate fecha;
    @Override
    public String toString(){
        return GsonProviter.getGson().toJson(this);
    }
}
