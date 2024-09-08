package proyecto.integrador.clinica.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyecto.integrador.clinica.utils.GsonProviter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "odontologos")

public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String numeromatricula;
    private String nombre;
    private String apellido;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "odontologo-turnos")
   // @JsonIgnore

    private Set<Turno> turnoSet;


    @Override
    public String toString() {
        return GsonProviter.getGson().toJson(this);
    }
}
