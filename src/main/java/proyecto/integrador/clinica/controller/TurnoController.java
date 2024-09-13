package proyecto.integrador.clinica.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.integrador.clinica.dto.request.TurnoModificarDto;
import proyecto.integrador.clinica.dto.request.TurnoRequestDto;
import proyecto.integrador.clinica.dto.response.TurnoResponseDto;
import proyecto.integrador.clinica.service.ITurnoService;
import proyecto.integrador.clinica.service.impl.TurnoService;
import proyecto.integrador.clinica.entity.Turno;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
   private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    private static final Logger logger = LoggerFactory.getLogger(TurnoController.class);

    @PostMapping("/guardar")
    public ResponseEntity<TurnoResponseDto> guardarTurno(@Valid @RequestBody TurnoRequestDto turnoRequestDto){
        logger.info("Guardando turno: {}", turnoRequestDto);
        return ResponseEntity.ok(turnoService.guardarTurno(turnoRequestDto));
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<TurnoResponseDto>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarTurno(@Valid @RequestBody TurnoModificarDto turnoModificarDto){
        logger.info("Modificando turno con ID: {}", turnoModificarDto.getId());
        turnoService.modificarTurnos(turnoModificarDto);
        return ResponseEntity.ok("{\"mensaje\": \"El turno fue modificado\"}");
    }

    @GetMapping("/buscarTurnoApellido/{apellido}")
    public ResponseEntity<TurnoResponseDto> buscarTurnoPorApellido(@PathVariable String apellido){
        Optional<TurnoResponseDto> turno = turnoService.buscarTurnosPorPaciente(apellido);
        if (turno.isPresent()) {
            return ResponseEntity.ok(turno.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // O manejar de otra manera si prefieres
        }
    }


    @GetMapping("/buscarTurnoDni/{dni}")
    public ResponseEntity<TurnoResponseDto> buscarTurnoPorDni(@PathVariable String dni){
        Optional<TurnoResponseDto> turno = turnoService.buscarporDniPaciente(dni);
        if (turno.isPresent()) {
            return ResponseEntity.ok(turno.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new TurnoResponseDto());
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id){
        logger.info("Eliminando turno con ID: {}", id);
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("{\"mensaje\": \"El turno fue eliminado\"}");
    }
}
