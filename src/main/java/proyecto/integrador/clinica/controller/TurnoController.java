package proyecto.integrador.clinica.controller;

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

    @PostMapping("/guardar")
    public ResponseEntity<TurnoResponseDto> guardarTurno(@RequestBody TurnoRequestDto turnoRequestDto){
        return ResponseEntity.ok(turnoService.guardarTurno(turnoRequestDto));
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<TurnoResponseDto>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarTurno(@RequestBody TurnoModificarDto turnoModificarDto){
        turnoService.modificarTurnos(turnoModificarDto);
        return ResponseEntity.ok("{\"mensaje\": \"El paciente fue modificado\"}");
    }

    @GetMapping("/buscarTurnoApellido/{apellido}")
    public ResponseEntity<TurnoResponseDto> buscarTurnoPorApellido(@PathVariable String apellido){
        Optional<TurnoResponseDto> turno = turnoService.buscarTurnosPorPaciente(apellido);
        return ResponseEntity.ok(turno.get());
    }

    @GetMapping("/buscarTurnoDni/{dni}")
    public ResponseEntity<TurnoResponseDto> buscarTurnoPorDni(@PathVariable String dni){
        Optional<TurnoResponseDto> turno = turnoService.buscarporDniPaciente(dni);
        return ResponseEntity.ok(turno.get());
    }
}
