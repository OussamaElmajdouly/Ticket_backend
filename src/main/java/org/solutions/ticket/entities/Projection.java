package org.solutions.ticket.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Projection {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date dateProjection;
    private double prix;
    @ManyToOne
    private Salle salle;
    @ManyToOne
    private  Film film;
    @OneToMany(mappedBy = "projection")
    private List<Ticket> tickets;
    @ManyToOne
    private Seance seance;
}
