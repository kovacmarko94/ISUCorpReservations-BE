package IsuCorpReservations.IsuCorpReservations.service;

import IsuCorpReservations.IsuCorpReservations.model.Contact;
import IsuCorpReservations.IsuCorpReservations.model.Reservation;
import IsuCorpReservations.IsuCorpReservations.repository.ContactRepository;
import IsuCorpReservations.IsuCorpReservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ContactRepository contactRepository;

    public Reservation save(Reservation reservation) throws Exception {
        final Contact reservationContact = reservation.getContact();
        Contact contact = contactRepository.findByName(reservationContact.getName());
        if (contact == null) {
            Contact newContact = contactRepository.save(reservationContact);
            if (newContact == null) {
                throw new Exception("Unable to save reservation contact!");
            }
        }
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation findById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (!reservation.isPresent()) {
            return null;
        }
        return reservation.get();
    }
}
