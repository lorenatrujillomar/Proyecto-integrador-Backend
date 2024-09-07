package proyecto.integrador.clinica.service.impl;

import org.springframework.stereotype.Service;
import proyecto.integrador.clinica.entity.Odontologo;
import proyecto.integrador.clinica.repository.IOdontologoRepository;
import proyecto.integrador.clinica.service.IOdontologoService;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }


    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo);

    }

    @Override
    public void eliminarOdontologo(Integer id) {
        odontologoRepository.deleteById(id);

    }
}
