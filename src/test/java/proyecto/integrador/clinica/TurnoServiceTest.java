package proyecto.integrador.clinica;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import proyecto.integrador.clinica.dto.request.OdontologoRequestDto;
import proyecto.integrador.clinica.dto.request.PacienteRequestDto;
import proyecto.integrador.clinica.dto.request.TurnoRequestDto;
import proyecto.integrador.clinica.dto.response.OdontologoResponseDto;
import proyecto.integrador.clinica.dto.response.PacienteResponseDto;
import proyecto.integrador.clinica.dto.response.TurnoResponseDto;
import proyecto.integrador.clinica.service.impl.OdontologoService;
import proyecto.integrador.clinica.service.impl.PacienteService;
import proyecto.integrador.clinica.service.impl.TurnoService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class TurnoServiceTest {
    static final Logger logger = LoggerFactory.getLogger(TurnoServiceTest.class);

    @Autowired
    TurnoService turnoService;
    TurnoRequestDto turnoRequestDto;
    TurnoResponseDto turnoDesdeBD;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    @BeforeEach
    void cargarDatos() {
        PacienteRequestDto pacienteRequestDto = new PacienteRequestDto();
        pacienteRequestDto.setNombre("Juan");
        pacienteRequestDto.setApellido("Perez");
        pacienteRequestDto.setDni("12345678");
        PacienteResponseDto pacienteGuardado =  pacienteService.guardarPaciente(pacienteRequestDto);

        // Crear un odontólogo
        OdontologoRequestDto odontologoRequestDto = new OdontologoRequestDto();
        odontologoRequestDto.setNumeromatricula("12345");
        odontologoRequestDto.setNombre("Dr. Gomez");
        odontologoRequestDto.setApellido("Gomez");
        OdontologoResponseDto odontologoGuardado = odontologoService.guardarOdontologo(odontologoRequestDto);

        turnoRequestDto = new TurnoRequestDto();
        turnoRequestDto.setPaciente_id(pacienteGuardado.getId());
        turnoRequestDto.setOdontologo_id(odontologoGuardado.getId());
        turnoRequestDto.setFecha(LocalDate.of(2024,9,16));

        turnoDesdeBD = turnoService.guardarTurno(turnoRequestDto);
    }

    @Test
    @DisplayName("Testear que un turno fue cargado correctamente")
    void caso1(){
        assertNotNull(turnoDesdeBD.getId());
    }

    @Test
    @DisplayName("Testear que un turno pueda acceder por id")
    void caso2(){
        //Dado
        Integer id = turnoDesdeBD.getId();
        //cuando
        TurnoResponseDto  turnoRecuperado = turnoService.buscarPorId(id).get();
        // entonces
        assertEquals(id, turnoRecuperado.getId());
    }

    @Test
    @DisplayName("Testear que liste los turnos")
    void caso3(){
        //Dado
        List<TurnoResponseDto> turnos;
        //Cuando
        turnos = turnoService.buscarTodos();
        //Entonces
        assertFalse(turnos.isEmpty());
    }
}