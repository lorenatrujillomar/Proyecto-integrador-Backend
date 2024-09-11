package proyecto.integrador.clinica.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.integrador.clinica.config.ModelMapperConfig;
import proyecto.integrador.clinica.dto.request.PacienteModificarDto;
import proyecto.integrador.clinica.dto.request.PacienteRequestDto;
import proyecto.integrador.clinica.dto.response.DomicilioResponseDto;
import proyecto.integrador.clinica.dto.response.PacienteResponseDto;
import proyecto.integrador.clinica.entity.Domicilio;
import proyecto.integrador.clinica.entity.Paciente;
import proyecto.integrador.clinica.exception.ResourceNotFoundException;
import proyecto.integrador.clinica.repository.IPacienteRepository;
import proyecto.integrador.clinica.service.IPacienteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private IPacienteRepository pacienteRepository;
    @Autowired
    private ModelMapper modelMapper;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public PacienteResponseDto guardarPaciente(PacienteRequestDto pacienteRequestDto) {
        Paciente paciente = modelMapper.map(pacienteRequestDto, Paciente.class);
        if(pacienteRequestDto.getDomicilio() != null) {
            Domicilio domicilio = modelMapper.map(pacienteRequestDto.getDomicilio(), Domicilio.class);
            paciente.setDomicilio(domicilio);
        }

        Paciente pacienteDesdeBD = pacienteRepository.save(paciente);
        return convertirPacienteEnResponse(pacienteDesdeBD);
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
        if(pacienteEncontrado.isPresent()){
            return pacienteEncontrado;
        } else {
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

    @Override
    public List<PacienteResponseDto> buscarTodos() {
        List<Paciente> pacientesDesdeBD = pacienteRepository.findAll();
        List<PacienteResponseDto> pacientesRespuesta = new ArrayList<>();
        for (Paciente p : pacientesDesdeBD) {
            pacientesRespuesta.add(convertirPacienteEnResponse(p));
        }
        return pacientesRespuesta;
    }

    @Override
    public void modificarPaciente(PacienteModificarDto pacienteModificarDto) {
        Optional<Paciente> paciente = pacienteRepository.findById(pacienteModificarDto.getId());
        if (paciente.isPresent()) {
            Paciente pacienteModificar = paciente.get();
            pacienteModificar.setNombre(pacienteModificarDto.getNombre());
            pacienteModificar.setApellido(pacienteModificarDto.getApellido());
            pacienteModificar.setDni(pacienteModificarDto.getDni());
            pacienteRepository.save(pacienteModificar);
        }
    }

    @Override
    public void eliminarPaciente(Integer id) {
        Optional<Paciente> pacienteEncontrado = buscarPorId(id);
        if (pacienteEncontrado.isPresent()){
            pacienteRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Paciente no encontrado");
        }


    }

    private PacienteResponseDto convertirPacienteEnResponse(Paciente paciente) {
        PacienteResponseDto requestDto = modelMapper.map(paciente, PacienteResponseDto.class);
        if(paciente.getDomicilio() != null){
            requestDto.setDomicilio(modelMapper.map(paciente.getDomicilio(), DomicilioResponseDto.class));
        }
        return requestDto;
    }

    @Override
    public List<Paciente> buscarPorApellido(String apellido){
        return pacienteRepository.findByApellido(apellido);
    }

    @Override
    public List<Paciente> buscarPorUnaParteApellido(String parte){
        return pacienteRepository.buscarPorParteApellido(parte);
    }
}
