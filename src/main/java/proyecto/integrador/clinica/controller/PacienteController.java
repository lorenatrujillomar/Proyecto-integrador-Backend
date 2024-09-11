package proyecto.integrador.clinica.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.integrador.clinica.dto.request.PacienteModificarDto;
import proyecto.integrador.clinica.dto.request.PacienteRequestDto;
import proyecto.integrador.clinica.dto.request.TurnoModificarDto;
import proyecto.integrador.clinica.dto.request.TurnoRequestDto;
import proyecto.integrador.clinica.dto.response.PacienteResponseDto;
import proyecto.integrador.clinica.dto.response.TurnoResponseDto;
import proyecto.integrador.clinica.entity.Odontologo;
import proyecto.integrador.clinica.entity.Paciente;
import proyecto.integrador.clinica.entity.Turno;
import proyecto.integrador.clinica.exception.BadRequestException;
import proyecto.integrador.clinica.exception.ResourceNotFoundException;
import proyecto.integrador.clinica.service.IPacienteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //POST
    @PostMapping("/guardar")
    public ResponseEntity<PacienteResponseDto> guardarPaciente(@RequestBody PacienteRequestDto pacienteRequestDto){
        PacienteResponseDto pacienteResponseDto = pacienteService.guardarPaciente(pacienteRequestDto);
        return  ResponseEntity.ok(pacienteResponseDto);
    }


    //PUT
    @PutMapping("/modificar")
    public ResponseEntity<String> modificarTurno(@RequestBody PacienteModificarDto pacienteModificarDto){
        pacienteService.modificarPaciente(pacienteModificarDto);
        return ResponseEntity.ok("{\"mensaje\": \"El paciente fue modificado\"}");
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id){

            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("{\"mensaje\": \"El paciente fue eliminado\"}");
    }


    //GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Integer id) {
        Optional<Paciente> pacienteEncontrado = pacienteService.buscarPorId(id);
        if (pacienteEncontrado.isPresent()) {
            return ResponseEntity.ok(pacienteEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //GET
    @GetMapping("/buscartodos")
    public ResponseEntity<List<PacienteResponseDto>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/buscarApellido/{apellido}")
    public ResponseEntity<List<Paciente>> buscarApellido(@PathVariable String apellido){
        return ResponseEntity.ok(pacienteService.buscarPorApellido(apellido));
    }

    @GetMapping("/buscarApellidoParte/{parte}")
    public ResponseEntity<List<Paciente>> buscarPorUnaParteApellido(@PathVariable String parte){
        return ResponseEntity.ok(pacienteService.buscarPorUnaParteApellido(parte));
    }
}
