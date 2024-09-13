package proyecto.integrador.clinica.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.integrador.clinica.dto.request.OdontologoModificarDto;
import proyecto.integrador.clinica.dto.request.OdontologoRequestDto;
import proyecto.integrador.clinica.dto.response.OdontologoResponseDto;
import proyecto.integrador.clinica.dto.response.PacienteResponseDto;
import proyecto.integrador.clinica.entity.Odontologo;
import proyecto.integrador.clinica.entity.Paciente;
import proyecto.integrador.clinica.exception.ResourceNotFoundException;
import proyecto.integrador.clinica.repository.IOdontologoRepository;
import proyecto.integrador.clinica.service.IOdontologoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository odontologoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    @Override
    public OdontologoResponseDto guardarOdontologo(OdontologoRequestDto odontologoRequestDto) {
        logger.info("Guardando odontólogo: {}", odontologoRequestDto);
        Odontologo odontologo = modelMapper.map(odontologoRequestDto, Odontologo.class);
        Odontologo odontologoDesdeBD = odontologoRepository.save(odontologo);
        return convertirOdontologoEnResponse(odontologoDesdeBD);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        Optional<Odontologo> odontologoEncontrado = odontologoRepository.findById(id);
        if(odontologoEncontrado.isPresent()){
            return odontologoEncontrado;
        } else {
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
    }

    @Override
    public List<OdontologoResponseDto> buscarTodos() {
        List<Odontologo> odontologosDesdeBD = odontologoRepository.findAll();
        List<OdontologoResponseDto> odontologosResponse = new ArrayList<>();
        for(Odontologo o: odontologosDesdeBD){
            odontologosResponse.add(convertirOdontologoEnResponse(o));
        }
        return odontologosResponse;
    }

    @Override
    public void modificarOdontologo(OdontologoModificarDto odontologoModificarDto) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(odontologoModificarDto.getId());
        if (odontologo.isPresent()) {
            Odontologo odotologoModificar = odontologo.get();
            odotologoModificar.setNumeromatricula(odontologoModificarDto.getNumeromatricula());
            odotologoModificar.setApellido(odontologoModificarDto.getApellido());
            odotologoModificar.setNombre(odontologoModificarDto.getNombre());
            odontologoRepository.save(odotologoModificar);
            logger.info("Odontólogo con ID {} modificado exitosamente", odontologoModificarDto.getId());
        } else {
            logger.error("Odontólogo con ID {} no encontrado para modificar", odontologoModificarDto.getId());
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        logger.info("Eliminando odontólogo con ID: {}", id);
        Optional<Odontologo> odontologoEncontrado = buscarPorId(id);
        if (odontologoEncontrado.isPresent()){
            odontologoRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }


    }

    private OdontologoResponseDto convertirOdontologoEnResponse(Odontologo odontologo){
        OdontologoResponseDto requestDto = modelMapper.map(odontologo, OdontologoResponseDto.class);
        return requestDto;
    }

    @Override
    public List<Odontologo> buscarPorUnaParteNumeroMatricula(String matricula){
        return odontologoRepository.buscarPorUnaParteNumeroMatricula(matricula);
    }
}
