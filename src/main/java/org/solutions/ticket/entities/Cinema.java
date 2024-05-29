package org.solutions.ticket.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor

public class Cinema  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String name;
    private double longitude,latitude,altitude;
    private int nbr_salles;

    @OneToMany(mappedBy = "cinema",fetch = FetchType.LAZY)

    private List<Salle> Salles;
    @ManyToOne
    private Ville ville;
}
