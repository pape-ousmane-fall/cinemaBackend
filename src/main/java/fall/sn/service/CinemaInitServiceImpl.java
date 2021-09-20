package fall.sn.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fall.sn.model.Categorie;
import fall.sn.model.Cinema;
import fall.sn.model.Film;
import fall.sn.model.Place;
import fall.sn.model.ProjectionFilm;
import fall.sn.model.Salle;
import fall.sn.model.Seance;
import fall.sn.model.Ticket;
import fall.sn.model.Ville;
import fall.sn.repository.CategorieRepository;
import fall.sn.repository.CinemaRepository;
import fall.sn.repository.FilmRepository;
import fall.sn.repository.PlaceRepository;
import fall.sn.repository.ProjectionRepository;
import fall.sn.repository.SalleRepository;
import fall.sn.repository.SeanceRepository;
import fall.sn.repository.TicketRepository;
import fall.sn.repository.VilleRepository;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaService{
	
public CinemaInitServiceImpl(VilleRepository villeRepository, CategorieRepository categorieRepository,
			CinemaRepository cinemaRepository, FilmRepository filmRepository, PlaceRepository placeRepository,
			ProjectionRepository projectionRepository, SalleRepository salleRepository,
			fall.sn.repository.SeanceRepository seanceRepository, TicketRepository ticketRepository) {
		super();
		this.villeRepository = villeRepository;
		this.categorieRepository = categorieRepository;
		this.cinemaRepository = cinemaRepository;
		this.filmRepository = filmRepository;
		this.placeRepository = placeRepository;
		this.projectionRepository = projectionRepository;
		this.salleRepository = salleRepository;
		this.seanceRepository = seanceRepository;
		this.ticketRepository = ticketRepository;
	}

private VilleRepository villeRepository;
private CategorieRepository categorieRepository;
private CinemaRepository cinemaRepository;
private FilmRepository filmRepository;
private PlaceRepository placeRepository;
private ProjectionRepository projectionRepository;
private SalleRepository salleRepository;
private SeanceRepository seanceRepository;
private TicketRepository ticketRepository;
	@Override
	public void initVilles() {
		// TODO Auto-generated method stub
		Stream.of("Casablanca","Marrakech","Rabat","Tanger").forEach(nameVille->{
			Ville ville=new Ville();
			ville.setName(nameVille);
			villeRepository.save(ville);
		});
	}

	@Override
	public void initCinema() {
		// TODO Auto-generated method stub
		villeRepository.findAll().forEach(ville->{
			Stream.of("MegaRama","IMAX","FOUNOUN","CHAHRAZAD","DAOULIZ")
			.forEach(nameCinema->{
				Cinema cinema=new Cinema();
//				cinema.setName(nameCinema);
				cinema.setNombreSalles(3+(int)(Math.random()*7));
				cinema.setVille(ville);
				cinemaRepository.save(cinema);
			});
			
		});
		 
	}

	@Override
	public void initsalles() {
		// TODO Auto-generated method stub
		cinemaRepository.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNombreSalles();i++) {
				Salle salle=new Salle();
				salle.setName("Salle "+(i+1));
				salle.setCinema(cinema);
				salle.setNombrePlaces(20+(int)(Math.random()*10));
				salleRepository.save(salle);
			}
		});
	}

	@Override
	public void initPlaces() {
		// TODO Auto-generated method stub
		salleRepository.findAll().forEach(salle->{
			for(int i=0;i<salle.getNombrePlaces();i++) {
				Place place=new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
				placeRepository.save(place);
			}
		});
		
	}

	@Override
	public void initSeance() {
		DateFormat dateFormat=new SimpleDateFormat("HH:mm");
		// TODO Auto-generated method stub
		Stream.of("12:00","13:00","15:00","17:00","19:00","21:00").forEach(s->{
			Seance seance=new Seance();
			
			try {
				seance.setHeurDebut(dateFormat.parse(s)); 
				seanceRepository.save(seance);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initCategories() {
		// TODO Auto-generated method stub
		Stream.of("Histoir","Action","Fiction","Drama").forEach(cat->{
			Categorie categorie=new Categorie();
			categorie.setName(cat);
			categorieRepository.save(categorie);
		});
	}

	@Override
	public void initFilms() {
		// TODO Auto-generated method stub
		List<Categorie> categorie=categorieRepository.findAll();
		double[] durees=new double[] {1,1.5,2,2.5,3};
		Stream.of("Game of the trones","Spider Man","Bad man","Iron man","Viking").forEach(fil->{
			Film film=new Film();
			film.setTitre(fil);
			film.setDuree(durees[new Random().nextInt(durees.length)]);
			film.setPhoto(fil.replaceAll("", "")+".jpg");
			film.setCategorie(categorie.get(new Random().nextInt(categorie.size())));
			filmRepository.save(film);
		});
	}

	@Override
	public void initProjection() {
		// TODO Auto-generated method stub
		double[] prix=new double[] {30,50,60,70,90,100};
		villeRepository.findAll().forEach(ville->{
			ville.getCinema().forEach(cinema->{
				filmRepository.findAll().forEach(film->{
					seanceRepository.findAll().forEach(seance->{
						ProjectionFilm projection=new ProjectionFilm();
						projection.setDateProjection(new Date());
						projection.setFilm(film);
						projection.setPrix(prix[new Random().nextInt(prix.length)]);
						projectionRepository.save(projection);
					});
				});
			});
		});
		
	}

	@Override
	public void initTickets() {
		// TODO Auto-generated method stub
		projectionRepository.findAll().forEach(p->{
			p.getSalle().getPlace().forEach(place->{
				Ticket ticket  =new Ticket();
				ticket.setPlace(place);
				ticket.setProjection(p);
				ticket.setReserve(false);
				ticketRepository.save(ticket);
			});
		});
	}

}
