package org.solutions.ticket;

import org.solutions.ticket.Services.CinemaService;
import org.solutions.ticket.entities.Film;
import org.solutions.ticket.entities.Salle;
import org.solutions.ticket.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class TicketApplication implements CommandLineRunner {
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private RepositoryRestConfiguration restConfiguration;
    public static void main(String[] args) {

        SpringApplication.run(TicketApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       restConfiguration.exposeIdsFor(Film.class, Salle.class, Ticket.class);

        cinemaService.initVilles();
        cinemaService.initCinemas();
        cinemaService.initSalles();
        cinemaService.initPlaces();
        cinemaService.initSeances();
        cinemaService.initCategories();
        cinemaService.Films();
        cinemaService.initProjections();
        cinemaService.initTickets();


    }
}
