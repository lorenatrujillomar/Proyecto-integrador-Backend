package proyecto.integrador.clinica;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proyecto.integrador.clinica.dao.impl.DaoH2Odontologo;
import proyecto.integrador.clinica.db.H2Connection;
import proyecto.integrador.clinica.model.Odontologo;
import proyecto.integrador.clinica.service.OdontologoService;



import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OdontologoServiceTest {
    static final Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);
    OdontologoService odontologoService = new OdontologoService(new DaoH2Odontologo());

    @BeforeAll
    static void crearTablas() {
        H2Connection.crearTablas();
    }
    @Test
    @DisplayName("Testear que un odontólogo fue cargado correctamente")
    void caso1(){
        //Dado
        Odontologo odontologo = new Odontologo(65412369, "Andres", "Castro");
        //Cuando
        Odontologo odontologoDesdeDb = odontologoService.guardarOdontologo(odontologo);
        //Entonces
        assertNotNull(odontologoDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que un odontologo pueda acceder por id")
    void caso2(){
        //Dado
        Integer id = 1;
        //cuando
         Odontologo odontologodesdeDb = odontologoService.buscarPorId(id);
        // entonces
        assertEquals(id, odontologodesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que liste los odontologos")
    void caso3(){
        //Dado
        List<Odontologo> odontologos;
        //Cuando
        odontologos = odontologoService.buscarTodos();
        //Entonces
        assertNotNull(odontologos);
    }
}