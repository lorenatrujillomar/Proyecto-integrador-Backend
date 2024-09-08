package proyecto.integrador.clinica.repository;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.integrador.clinica.dto.response.TurnoResponseDto;
import proyecto.integrador.clinica.entity.Turno;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Integer> {

    //from Product p inner join p.category with p.price > 500
    @Query("Select t  from Turno t join t.paciente p with p.apellido = :pacienteApellido ")
    Optional<Turno> buscarPorApellidoPaciente(String pacienteApellido);

    @Query("SELECT t FROM Turno t JOIN t.paciente p JOIN t.odontologo o WHERE p.dni = :pacienteDni")
    List<Turno> buscarPorDniPaciente(@Param("pacienteDni") String pacienteDni);
}
