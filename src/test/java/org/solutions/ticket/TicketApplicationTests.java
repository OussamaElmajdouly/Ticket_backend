package org.solutions.ticket;

import org.junit.jupiter.api.Test;
import org.solutions.ticket.Services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TicketApplicationTests implements CommandLineRunner {
@Autowired
    private CinemaService cinemaService;
    @Test
    void contextLoads() {
    }

    @Override
    public void run(String... args) throws Exception {
        cinemaService.initVilles();
        cinemaService.initCinemas();
        cinemaService.initSalles();
        cinemaService.initPlaces();
        cinemaService.initSeances();
        cinemaService.Films();
        cinemaService.initTickets();

    }
}
