package org.solutions.ticket.Services;

import jakarta.transaction.Transactional;
import org.solutions.ticket.Repository.*;
import org.solutions.ticket.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaServiceImp implements CinemaService{
    @Autowired
private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public  List<Ville>  initVilles() {
        Stream.of("Casablanca","Marrakech","Rabat","Tanger").forEach(nameVille->{
            Ville ville = new Ville();
            ville.setName(nameVille);
            villeRepository.save(ville);
        });

        return villeRepository.findAll();
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v->{
            Stream.of("Megarama","IMax","Pathe")
                    .forEach(nameCinema->{
                        Cinema cinema = new Cinema();
                        cinema.setName(nameCinema);
                        cinema.setNbr_salles(3+(int)(Math.random()*7));
                        cinema.setVille(v);
                        cinemaRepository.save(cinema);

                    });
        });
    }

    @Override
    public void initSalles() {
     cinemaRepository.findAll().forEach(cinema->{
         for (int i=0;i<cinema.getNbr_salles();i++){
             Salle salle=new Salle();
             salle.setName("Salle"+(i+1));
             salle.setCinema(cinema);
             salle.setNbr_place(15+(int)(Math.random()*20));
             salleRepository.save(salle);

         }

     });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i=0;i< salle.getNbr_place();i++){
                Place place=new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });

    }

    @Override
    public void initSeances() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("13:00","16:00","19:00","22:00","00:00").forEach(s->{
                    Seance seance =new Seance();
                    try {
                        seance.setHeureDebut(dateFormat.parse(s));
                        seanceRepository.save(seance);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                );

    }

    @Override
    public void initCategories() {
        Stream.of("Histoire","Actions","fiction","Drama").forEach(cat->{
            Categorie categorie = new Categorie();
            categorie.setName(cat);
            categorieRepository.save(categorie);
        });
    }

    @Override
    public void Films() {
        double[] duree=new double[] {3.00,3.10,3.11,3.14,3.05,3.08,3.00,3.00,3.03,3.02};
        List<Categorie> categories=categorieRepository.findAll();
Stream.of("Art of Love","Damsel","Love Divided","Marei the Primo","Stolen"
        ,"The Protege","The Tearsmith","The Wages of Fear","What Jennifer Did",
        "Woodpecker Goes to Camp").forEach(titrefilm->{
           Film film = new Film();
           film.setTitre(titrefilm);
           film.setDuree(duree[new Random().nextInt(duree.length)]);
           film.setPhoto(titrefilm.replaceAll(" ","")+".png");
           film.setCategorie(categories.get(new Random().nextInt(categories.size())));
           filmRepository.save(film);

});
    }

    @Override
    public void initProjections() {
        double[] prices = new double[] {30,40,50,60,70,80,90,100};
        List<Film> films=filmRepository.findAll();

        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    int index=new Random().nextInt(films.size());
                    Film film=films.get(index);

                        seanceRepository.findAll().forEach(seance -> {
                            Projection projection = new Projection();
                            projection.setDateProjection(new Date());
                            projection.setFilm(film);
                            projection.setPrix(prices[new Random().nextInt(prices.length)]);
                            projection.setSalle(salle);
                            projection.setSeance(seance);
                            projectionRepository.save(projection);

                    });
                });
            });
        });

    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(p->{
            p.getSalle().getPlaces().forEach(place -> {
                Ticket ticket=new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(p.getPrix());
                ticket.setProjection(p);
                ticket.setReserve(false);
                ticketRepository.save(ticket);

            });
        });

    }
}
