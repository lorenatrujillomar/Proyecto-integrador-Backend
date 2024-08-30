package proyecto.integrador.clinica.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.integrador.clinica.model.Odontologo;
import proyecto.integrador.clinica.model.Turno;
import proyecto.integrador.clinica.service.OdontologoService;


import java.util.List;


@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService= odontologoService;
    }

    //POST
    @PostMapping("/guardar")
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }

    //PUT
    @PutMapping("/modificar")
    public ResponseEntity<String> modificarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologoEncontrado =   odontologoService.buscarPorId(odontologo.getId());
        if(odontologoEncontrado != null) {
            odontologoService.modificarOdontologo(odontologo);
            String jsonResponse = "{\"mensaje\": \"El odontologo fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        }else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id){
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(id);
        if(odontologoEncontrado != null) {
            odontologoService.eliminarOdontologo(id);
            String jsonResponse = "{\"mensaje\": \"El odontólogo fue eliminado\"}";
            return ResponseEntity.ok(jsonResponse);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id) {
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(id);
        if (odontologoEncontrado != null) {
            return ResponseEntity.ok(odontologoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //GET
    @GetMapping("/buscartodos")
    public List<Odontologo> buscarTodos(){
        return odontologoService.buscarTodos();
    }
}
