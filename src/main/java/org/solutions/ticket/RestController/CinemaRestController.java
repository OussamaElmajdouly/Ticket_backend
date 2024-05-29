package org.solutions.ticket.RestController;


import ch.qos.logback.core.model.Model;
import lombok.Data;
import org.solutions.ticket.Repository.FilmRepository;
import org.solutions.ticket.Repository.TicketRepository;
import org.solutions.ticket.Services.CinemaService;
import org.solutions.ticket.entities.Film;
import org.solutions.ticket.entities.Ticket;
import org.solutions.ticket.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaRestController {
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    TicketRepository ticketRepository;
    @GetMapping( path="/imagefilm/{id}",produces= MediaType.IMAGE_PNG_VALUE)
 public byte[] image(@PathVariable(name="id")Long id) throws Exception{
        Film f=filmRepository.findById(id).get();
        String photoName=f.getPhoto();
        File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoName );
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
 }

 @PostMapping("/payerTickets")
 public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
        List<Ticket> listTickets=new ArrayList<>();
ticketForm.getTickets().forEach(idTicket->{
    Ticket ticket=ticketRepository.findById(idTicket).get();
    ticket.setNomClient(ticketForm.getNomClient());
    ticket.setReserve(true);
    ticketRepository.save(ticket);
    listTickets.add(ticket);

} );
return listTickets;
 }
}
@Data
class TicketForm{
    private String nomClient;
    private List<Long> tickets=new ArrayList<>();
}