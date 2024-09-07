package proyecto.integrador.clinica.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.guardarTurno(turno));
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    //PUT
    @PutMapping("/modificar")
    public ResponseEntity<String> modificarTurnos(@RequestBody Turno turno){
        Optional<Turno> turnoEncontrado =  turnoService.buscarPorId(turno.getId());
        if(turnoEncontrado.isPresent()){
            turnoService.modificarTurnos(turno);
            String jsonResponse = "{\"mensaje\": \"El turno fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id){
        Optional<Turno> turnoEncontrado = turnoService.buscarPorId(id);
        if(turnoEncontrado.isPresent()) {
             turnoService.eliminarTurno(id);
            String jsonResponse = "{\"mensaje\": \"El turno fue eliminado\"}";
            return ResponseEntity.ok(jsonResponse);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Integer id){
        Optional<Turno> turnoEncontrado = turnoService.buscarPorId(id);
        if(turnoEncontrado.isPresent()) {
            return ResponseEntity.ok(turnoEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
