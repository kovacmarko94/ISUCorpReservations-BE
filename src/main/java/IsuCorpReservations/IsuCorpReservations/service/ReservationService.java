package IsuCorpReservations.IsuCorpReservations.service;

import IsuCorpReservations.IsuCorpReservations.dto.ReservationDto;
import IsuCorpReservations.IsuCorpReservations.model.Contact;
import IsuCorpReservations.IsuCorpReservations.model.Reservation;
import IsuCorpReservations.IsuCorpReservations.repository.ContactRepository;
import IsuCorpReservations.IsuCorpReservations.repository.ReservationRepository;
import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private JTransfo jTransfo;

    @Autowired
    private ContactRepository contactRepository;

    public ReservationDto save(Reservation reservation) throws Exception {
        final Contact reservationContact = reservation.getContact();
        Contact contact = contactRepository.findByName(reservationContact.getName());
        if (contact == null) {
            Contact newContact = contactRepository.save(reservationContact);
            if (newContact == null) {
                throw new Exception("Unable to save reservation contact!");
            }
        }
        reservation = reservationRepository.save(reservation);
        return jTransfo.convertTo(reservation, ReservationDto.class);
    }

    public Page<Reservation> findAll(Pageable pageable) {
        Page<Reservation> reservations = reservationRepository.findAll(pageable);
        return reservations;
    }

    public Reservation findById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.isPresent() ? reservation.get() : null;
    }

    public ReservationDto update(Long id, Reservation newReservation) {
        Reservation reservation = findById(id);
        if (reservation == null) {
            throw new EntityNotFoundException(String.format("Reservation with %d does not exist! Update failed!", id));
        }
        reservation.setDateOfCreation(newReservation.getDateOfCreation());
        reservation.setRanking(newReservation.getRanking());
        reservation.setFavorite(newReservation.isFavorite());
        reservation.setHtmlContent(newReservation.getHtmlContent());
        reservation = reservationRepository.save(reservation);
        return jTransfo.convertTo(reservation, ReservationDto.class);
    }
}
