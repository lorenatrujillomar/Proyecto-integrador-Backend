package proyecto.integrador.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.integrador.clinica.entity.Domicilio;

@Repository
public interface IDomicilioRepository extends JpaRepository<Domicilio, Integer> {
}
