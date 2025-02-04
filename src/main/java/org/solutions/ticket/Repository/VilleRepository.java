package org.solutions.ticket.Repository;

import org.solutions.ticket.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")


public interface VilleRepository extends JpaRepository<Ville,Long> {
}
