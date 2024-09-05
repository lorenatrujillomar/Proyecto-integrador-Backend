package proyecto.integrador.clinica.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto.integrador.clinica.entity.Paciente;
import proyecto.integrador.clinica.service.impl.PacienteService;

@Controller
public class VistaController {

    private PacienteService pacienteService;

    public VistaController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/index")
    public String buscarPaciente(Model model, @RequestParam Integer id){
        Paciente paciente = pacienteService.buscarPorId(id).get();

        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        return "index";
    }
}
