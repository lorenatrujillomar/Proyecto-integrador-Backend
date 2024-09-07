package proyecto.integrador.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.integrador.clinica.entity.Turno;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Integer> {
}
