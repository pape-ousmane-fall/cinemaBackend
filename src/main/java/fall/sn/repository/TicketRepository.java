package fall.sn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fall.sn.model.Ticket;
@RepositoryRestResource
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
