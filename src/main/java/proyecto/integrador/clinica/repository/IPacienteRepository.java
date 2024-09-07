package proyecto.integrador.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.integrador.clinica.entity.Paciente;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
}
