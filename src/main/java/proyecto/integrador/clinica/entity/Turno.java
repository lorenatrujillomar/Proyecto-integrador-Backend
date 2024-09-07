package proyecto.integrador.clinica.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyecto.integrador.clinica.utils.GsonProviter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "turnos")

public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    //@JsonBackReference(value = "paciente-turnos")
    private Paciente paciente;

    @ManyToOne
    //@JsonBackReference(value = "odontologo-turnos")
    private Odontologo odontologo;
    private LocalDate fecha;

    @Override
    public String toString() {
        return GsonProviter.getGson().toJson(this);
    }
}
