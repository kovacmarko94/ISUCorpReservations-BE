package IsuCorpReservations.IsuCorpReservations.service;

import IsuCorpReservations.IsuCorpReservations.dto.ReservationDto;
import IsuCorpReservations.IsuCorpReservations.model.Contact;
import IsuCorpReservations.IsuCorpReservations.model.Reservation;
import IsuCorpReservations.IsuCorpReservations.repository.ContactRepository;
import IsuCorpReservations.IsuCorpReservations.repository.ReservationRepository;
import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<ReservationDto> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return jTransfo.convertList(reservations, ReservationDto.class);
    }

    public ReservationDto findById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (!reservation.isPresent()) {
            return null;
        }
        return jTransfo.convertTo(reservation.get(), ReservationDto.class);
    }
}
