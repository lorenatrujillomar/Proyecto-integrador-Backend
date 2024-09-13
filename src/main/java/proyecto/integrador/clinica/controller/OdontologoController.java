package proyecto.integrador.clinica.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.integrador.clinica.dto.request.OdontologoModificarDto;
import proyecto.integrador.clinica.dto.request.OdontologoRequestDto;
import proyecto.integrador.clinica.dto.response.OdontologoResponseDto;
import proyecto.integrador.clinica.entity.Odontologo;
import proyecto.integrador.clinica.entity.Paciente;
import proyecto.integrador.clinica.service.impl.OdontologoService;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService= odontologoService;
    }

    private static final Logger logger = LoggerFactory.getLogger(OdontologoController.class);
    //POST
    @PostMapping("/guardar")
    public ResponseEntity<OdontologoResponseDto> guardarOdontologo(@Valid @RequestBody OdontologoRequestDto odontologoRequestDto){
        logger.info("Guardando odontólogo: {}", odontologoRequestDto);
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologoRequestDto));
    }

    //PUT
    @PutMapping("/modificar")
    public ResponseEntity<String> modificarOdontologo(@Valid @RequestBody OdontologoModificarDto odontologoModificarDto){
        logger.info("Modificando odontólogo con ID: {}", odontologoModificarDto.getId());
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(odontologoModificarDto.getId());
        if (odontologoEncontrado.isPresent()) {
            odontologoService.modificarOdontologo(odontologoModificarDto);
            return ResponseEntity.ok("{\"mensaje\": \"El odontólogo fue modificado\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id){
        logger.info("Eliminando odontólogo con ID: {}", id);
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("{\"mensaje\": \"El odontologo fue eliminado\"}");
    }

    //GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id) {
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(id);
        if (odontologoEncontrado.isPresent()) {
            return ResponseEntity.ok(odontologoEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //GET
    @GetMapping("/buscartodos")
    public ResponseEntity<List<OdontologoResponseDto>> buscarTodos(){
        List<OdontologoResponseDto> odontologosResponseDto = odontologoService.buscarTodos();
        return ResponseEntity.ok(odontologosResponseDto);
    }

    @GetMapping("/buscarMatriculaParte/{matricula}")
    public ResponseEntity<List<Odontologo>> buscarPorUnaParteNumeroMatricula(@PathVariable String matricula){
        return ResponseEntity.ok(odontologoService.buscarPorUnaParteNumeroMatricula(matricula));
    }
}
