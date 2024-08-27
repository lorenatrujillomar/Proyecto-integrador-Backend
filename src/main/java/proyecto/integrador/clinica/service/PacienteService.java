package proyecto.integrador.clinica.service;



import org.springframework.stereotype.Service;
import proyecto.integrador.clinica.dao.IDao;
import proyecto.integrador.clinica.model.Paciente;

import java.util.List;

@Service
public class PacienteService {
    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteIDao.guardar(paciente);
    }

    public Paciente buscarPorId(Integer id){
        return pacienteIDao.buscarPorId(id);
    }
    public List<Paciente> buscarTodos(){
        return pacienteIDao.listaTodos();
    }
    public void modificarPaciente(Paciente paciente){
        pacienteIDao.modificar(paciente);
    }

    public void eliminarPaciente(Integer id){
        pacienteIDao.eliminar(id);
    }
}
