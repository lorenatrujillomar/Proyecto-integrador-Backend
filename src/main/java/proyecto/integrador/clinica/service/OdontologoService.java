package proyecto.integrador.clinica.service;


import org.springframework.stereotype.Service;
import proyecto.integrador.clinica.dao.IDao;
import proyecto.integrador.clinica.model.Odontologo;
import proyecto.integrador.clinica.model.Paciente;


import java.util.List;
@Service
public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

     public OdontologoService(IDao<Odontologo> odontologoIDao){
         this.odontologoIDao = odontologoIDao;
     }

     public  Odontologo guardarOdontologo(Odontologo odontologo){
         return odontologoIDao.guardar(odontologo);
     }

     public Odontologo buscarPorId(Integer id){
        return odontologoIDao.buscarPorId(id);
    }

     public List<Odontologo> buscarTodos() {
         return odontologoIDao.listarTodos();
     }

    public void modificarOdontologo(Odontologo odontologo){
        odontologoIDao.modificar(odontologo);
    }

    public void eliminarOdontologo(Integer id){odontologoIDao.eliminar(id);
    }
}

