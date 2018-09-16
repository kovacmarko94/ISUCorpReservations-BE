package IsuCorpReservations.IsuCorpReservations.repository;

import IsuCorpReservations.IsuCorpReservations.model.Reservation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {
}
