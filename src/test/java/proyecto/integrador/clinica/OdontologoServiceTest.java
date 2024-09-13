package proyecto.integrador.clinica;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import proyecto.integrador.clinica.dto.request.DomicilioRequestDto;
import proyecto.integrador.clinica.dto.request.OdontologoRequestDto;
import proyecto.integrador.clinica.dto.request.PacienteRequestDto;
import proyecto.integrador.clinica.dto.response.OdontologoResponseDto;
import proyecto.integrador.clinica.entity.Odontologo;
import proyecto.integrador.clinica.service.impl.OdontologoService;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class OdontologoServiceTest {
    static final Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);

    @Autowired
    OdontologoService odontologoService;
    OdontologoRequestDto odontologoRequestDto;
    OdontologoResponseDto odontologoDesdeDb;

    @BeforeEach
    void cargarDatos() {
        odontologoRequestDto = new OdontologoRequestDto();
        odontologoRequestDto.setNumeromatricula("342322434");
        odontologoRequestDto.setNombre("Maria");
        odontologoRequestDto.setApellido("Gomez");

        odontologoDesdeDb = odontologoService.guardarOdontologo(odontologoRequestDto);
    }

    @Test
    @DisplayName("Testear que un odontólogo fue cargado correctamente")
    void caso1(){
        assertNotNull(odontologoDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que un odontologo pueda acceder por id")
    void caso2(){
        //Dado
        Integer id = odontologoDesdeDb.getId();
        //cuando
         Odontologo odontologoRecuperado = odontologoService.buscarPorId(id).get();
        // entonces
        assertEquals(id, odontologoRecuperado.getId());
    }

    @Test
    @DisplayName("Testear que liste los odontologos")
    void caso3(){
        //Dado
        List<OdontologoResponseDto> odontologos;
        //Cuando
        odontologos = odontologoService.buscarTodos();
        //Entonces
        assertFalse(odontologos.isEmpty());
    }
}