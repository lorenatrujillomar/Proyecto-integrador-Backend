package proyecto.integrador.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.integrador.clinica.entity.Odontologo;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {

    @Query("SELECT o FROM Odontologo o WHERE LOWER(o.numeromatricula) LIKE LOWER(CONCAT('%',:numeromatricula,'%'))")
    List<Odontologo> buscarPorUnaParteNumeroMatricula(@Param("numeromatricula") String matricula);
}
