package org.solutions.ticket.Services;

import org.solutions.ticket.entities.Ville;

import java.util.List;

public interface CinemaService {
    public List<Ville> initVilles();
    public void initCinemas();
    public void initSalles();
    public void initPlaces();
    public void initSeances();
    public void initCategories();
    public void Films();
    public void initProjections();
    public void initTickets();
}
