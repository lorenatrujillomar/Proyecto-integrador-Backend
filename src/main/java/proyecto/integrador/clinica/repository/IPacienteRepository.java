package proyecto.integrador.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyecto.integrador.clinica.entity.Paciente;

import java.util.List;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {

    List<Paciente> findByApellido(String apellido);

    @Query("Select p from Paciente p where LOWER(p.apellido) LIKE LOWER(CONCAT('%',:parteApellido,'%'))")
    List<Paciente> buscarPorParteApellido(String parteApellido);
}
