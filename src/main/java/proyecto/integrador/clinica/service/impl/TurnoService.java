package proyecto.integrador.clinica.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.integrador.clinica.dto.request.TurnoModificarDto;
import proyecto.integrador.clinica.dto.request.TurnoRequestDto;
import proyecto.integrador.clinica.dto.response.OdontologoResponseDto;
import proyecto.integrador.clinica.dto.response.PacienteResponseDto;
import proyecto.integrador.clinica.dto.response.TurnoResponseDto;
import proyecto.integrador.clinica.entity.Odontologo;
import proyecto.integrador.clinica.entity.Paciente;
import proyecto.integrador.clinica.entity.Turno;
import proyecto.integrador.clinica.exception.BadRequestException;
import proyecto.integrador.clinica.exception.ResourceNotFoundException;
import proyecto.integrador.clinica.repository.ITurnoRepository;
import proyecto.integrador.clinica.service.IOdontologoService;
import proyecto.integrador.clinica.service.IPacienteService;
import proyecto.integrador.clinica.service.ITurnoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private final Logger logger = LoggerFactory.getLogger(TurnoService.class);

    private ITurnoRepository turnoRepository;
    private IPacienteService pacienteService;
    private IOdontologoService odontologoService;
    @Autowired
    private ModelMapper modelMapper;

    public TurnoService(ITurnoRepository turnoRepository, IPacienteService pacienteService, IOdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto){
        try{
            Optional<Paciente> paciente = pacienteService.buscarPorId(turnoRequestDto.getPaciente_id());
            Optional<Odontologo> odontologo = odontologoService.buscarPorId(turnoRequestDto.getOdontologo_id());
            Turno turno = new Turno();
            Turno turnoDesdeBD = null;
            TurnoResponseDto turnoResponseDto = null;

                //Armado del turno desde el turno request dto
                turno.setPaciente(paciente.get());
                turno.setOdontologo(odontologo.get());
                turno.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
                //Obtengo el turno persistido con el id
                turnoDesdeBD = turnoRepository.save(turno);

                //Armado del turno respondse dto desde el turno obtenido de la base de datos
                //turnoResponseDto = obtenerTurnoResponse(turnoDesdeBD);
                turnoResponseDto = convertirTurnoEnResponse(turnoDesdeBD);
                return turnoResponseDto;
        }catch (ResourceNotFoundException e){
            throw new BadRequestException("Paciente u odontologo no existen en la base de datos");
        }
    }

    @Override
    public Optional<TurnoResponseDto> buscarPorId(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        if(turno.isPresent()){
            TurnoResponseDto turnoRespuesta = convertirTurnoEnResponse(turno.get());
            return Optional.of(turnoRespuesta);
        } else {
            throw new ResourceNotFoundException("El turno no fue encontrado");
        }
    }

    @Override
    public List<TurnoResponseDto> buscarTodos() {
        List<Turno> turnosDesdeBD = turnoRepository.findAll();
        List<TurnoResponseDto> turnosRespuesta = new ArrayList<>();
        for(Turno t: turnosDesdeBD){
            //turnosRespuesta.add(obtenerTurnoResponse(t));
            TurnoResponseDto turnoRespuesta =convertirTurnoEnResponse(t);
            logger.info("turno "+ turnoRespuesta);

            turnosRespuesta.add(convertirTurnoEnResponse(t));
        }
        return turnosRespuesta;
    }

    @Override
    public void modificarTurnos(TurnoModificarDto turnoModificarDto) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(turnoModificarDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turnoModificarDto.getOdontologo_id());
        if(paciente.isPresent() && odontologo.isPresent()){
            Turno turno = new Turno(
                    turnoModificarDto.getId(),
                    paciente.get(), odontologo.get(),
                    LocalDate.parse(turnoModificarDto.getFecha())
                    );
            turnoRepository.save(turno);
        }
    }


    @Override
    public void eliminarTurno(Integer id){
        Optional<TurnoResponseDto> turnoEncontrado = buscarPorId(id);
            turnoRepository.deleteById(id);

    }

    private TurnoResponseDto obtenerTurnoResponse(Turno turnoDesdeBD){
        OdontologoResponseDto odontologoResponseDto = new OdontologoResponseDto(
                turnoDesdeBD.getOdontologo().getId(),
                turnoDesdeBD.getOdontologo().getNumeromatricula(),
                turnoDesdeBD.getOdontologo().getApellido(),
                turnoDesdeBD.getOdontologo().getNombre()
        );

        PacienteResponseDto pacienteResponseDto = new PacienteResponseDto();
        pacienteResponseDto.setId(turnoDesdeBD.getPaciente().getId());
        pacienteResponseDto.setApellido(turnoDesdeBD.getPaciente().getApellido());
        pacienteResponseDto.setNombre(turnoDesdeBD.getPaciente().getNombre());
        pacienteResponseDto.setDni(turnoDesdeBD.getPaciente().getDni());
        pacienteResponseDto.setFechaIngreso(turnoDesdeBD.getPaciente().getFechaIngreso().toString());

        TurnoResponseDto turnoResponseDto  = new TurnoResponseDto();
        turnoResponseDto.setId(turnoDesdeBD.getId());
        turnoResponseDto.setPacienteResponseDto(pacienteResponseDto);
        turnoResponseDto.setOdontologoResponseDto(odontologoResponseDto);
        turnoResponseDto.setFecha(turnoDesdeBD.getFecha().toString());

        return turnoResponseDto;
    }

    private TurnoResponseDto convertirTurnoEnResponse(Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setPacienteResponseDto(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        turnoResponseDto.setOdontologoResponseDto(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        return turnoResponseDto;
    }

    @Override
    public Optional<TurnoResponseDto> buscarTurnosPorPaciente(String pacienteApellido) {
        Optional<Turno> turno = turnoRepository.buscarPorApellidoPaciente(pacienteApellido);
        TurnoResponseDto turnoParaResponder = null;
        if(turno.isPresent()) {
            turnoParaResponder = convertirTurnoEnResponse(turno.get());
        }
        return Optional.ofNullable(turnoParaResponder);
    }

    @Override
    public Optional<TurnoResponseDto> buscarporDniPaciente(String pacienteDni){
        List<Turno> turnos = turnoRepository.buscarPorDniPaciente(pacienteDni);
        TurnoResponseDto turnoParaResponder = null;
        if(!turnos.isEmpty()) {
            turnoParaResponder = convertirTurnoEnResponse(turnos.get(0)); // O maneja la lista según tus necesidades
        }
        return Optional.ofNullable(turnoParaResponder);
    }
}
