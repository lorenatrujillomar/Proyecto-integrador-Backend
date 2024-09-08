package proyecto.integrador.clinica.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.integrador.clinica.dto.request.OdontologoModificarDto;
import proyecto.integrador.clinica.dto.request.OdontologoRequestDto;
import proyecto.integrador.clinica.dto.response.OdontologoResponseDto;
import proyecto.integrador.clinica.dto.response.PacienteResponseDto;
import proyecto.integrador.clinica.entity.Odontologo;
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


    @Override
    public OdontologoResponseDto guardarOdontologo(OdontologoRequestDto odontologoRequestDto) {
        Odontologo odontologo = modelMapper.map(odontologoRequestDto, Odontologo.class);
        Odontologo odontologoDesdeBD = odontologoRepository.save(odontologo);
        return convertirOdontologoEnResponse(odontologoDesdeBD);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        return odontologoRepository.findById(id);
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
        }
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        odontologoRepository.deleteById(id);

    }

    private OdontologoResponseDto convertirOdontologoEnResponse(Odontologo odontologo){
        OdontologoResponseDto requestDto = modelMapper.map(odontologo, OdontologoResponseDto.class);
        return requestDto;
    }
}
