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
import proyecto.integrador.clinica.dto.request.PacienteRequestDto;
import proyecto.integrador.clinica.dto.response.PacienteResponseDto;
import proyecto.integrador.clinica.entity.Domicilio;
import proyecto.integrador.clinica.entity.Paciente;
import proyecto.integrador.clinica.service.impl.PacienteService;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class PacienteServiceTest {
    static final Logger logger = LoggerFactory.getLogger(PacienteServiceTest.class);

    @Autowired
    PacienteService pacienteService;
    PacienteRequestDto pacienteRequestDto;
    PacienteResponseDto pacienteDesdeDb;

    @BeforeEach
    void cargarDatos() {
        DomicilioRequestDto domicilioRequestDto = new DomicilioRequestDto();
        domicilioRequestDto.setCalle("Falsa");
        domicilioRequestDto.setNumero(145);
        domicilioRequestDto.setLocalidad("Prueba");
        domicilioRequestDto.setProvincia("PruebaProvincia");

        pacienteRequestDto = new PacienteRequestDto();
        pacienteRequestDto.setApellido("Castro");
        pacienteRequestDto.setNombre("Maria");
        pacienteRequestDto.setDni("48974646");
        pacienteRequestDto.setFechaIngreso(LocalDate.of(2024,7,15));
        pacienteRequestDto.setDomicilio(domicilioRequestDto);

        pacienteDesdeDb = pacienteService.guardarPaciente(pacienteRequestDto);
    }

    @Test
    @DisplayName("Testear que un paciente fue cargado correctamente con su domicilio")
    void caso1(){
        assertNotNull(pacienteDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que un paciente pueda acceder por id")
    void caso2(){
        //Dado
        Integer id = pacienteDesdeDb.getId();
        //cuando
        Paciente pacienteRecuperado = pacienteService.buscarPorId(id).get();
        // entonces
        assertEquals(id, pacienteRecuperado.getId());
    }

    @Test
    @DisplayName("Listar todos los pacientes")
    void caso3(){
        //Dado
        List<PacienteResponseDto> pacientes;
        // cuando
        pacientes = pacienteService.buscarTodos();
        // entonces
        assertFalse(pacientes.isEmpty());
    }

}