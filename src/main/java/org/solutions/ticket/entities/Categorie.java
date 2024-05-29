package org.solutions.ticket.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Categorie {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)

private long id;
@Column(length = 75)
private String name;
@OneToMany(mappedBy = "categorie")
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private List<Film> films;
}
