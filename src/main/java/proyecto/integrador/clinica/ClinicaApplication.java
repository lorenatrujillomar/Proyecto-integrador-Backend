package proyecto.integrador.clinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import proyecto.integrador.clinica.db.H2Connection;

@SpringBootApplication
public class ClinicaApplication {

	public static void main(String[] args){
	H2Connection.crearTablas();
	SpringApplication.run(ClinicaApplication.class, args);

	}

}
