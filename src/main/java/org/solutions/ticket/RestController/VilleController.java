package org.solutions.ticket.RestController;


import ch.qos.logback.core.model.Model;
import org.solutions.ticket.Repository.VilleRepository;
import org.solutions.ticket.Services.CinemaService;
import org.solutions.ticket.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class VilleController {

    @Autowired
    private VilleRepository villeRepository;
    @GetMapping("/Cinema")
    public ModelAndView getAllVilles() {
        List<Ville> villes = villeRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("Cinema");
        modelAndView.addObject("listevilles", villes); // Ajoutez l'attribut à l'objet ModelAndView
        return modelAndView;

    }
}