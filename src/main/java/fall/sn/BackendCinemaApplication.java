package fall.sn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fall.sn.service.ICinemaService;

@SpringBootApplication
public class BackendCinemaApplication implements CommandLineRunner{
@Autowired
private ICinemaService iCinemaService;
	public static void main(String[] args) {
		SpringApplication.run(BackendCinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		iCinemaService.initVilles();
		iCinemaService.initCinema();
		iCinemaService.initsalles();
		iCinemaService.initPlaces();
		iCinemaService.initSeance();
		iCinemaService.initCategories();
		iCinemaService.initFilms();
		iCinemaService.initProjection();
		//iCinemaService.initTickets();
		
	}

}
