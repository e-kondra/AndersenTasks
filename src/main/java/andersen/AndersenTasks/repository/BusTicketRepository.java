package andersen.AndersenTasks.repository;

import andersen.AndersenTasks.models.BusTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BusTicketRepository extends CrudRepository<BusTicket, Long> {

    List<BusTicket> findAllByUserId(Long userId);
}
