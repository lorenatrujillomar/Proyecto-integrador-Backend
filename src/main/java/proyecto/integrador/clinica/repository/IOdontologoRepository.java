package proyecto.integrador.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.integrador.clinica.entity.Odontologo;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {
}
