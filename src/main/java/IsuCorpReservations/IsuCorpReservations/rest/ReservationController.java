package IsuCorpReservations.IsuCorpReservations.rest;

import IsuCorpReservations.IsuCorpReservations.dto.ReservationDto;
import IsuCorpReservations.IsuCorpReservations.model.Reservation;
import IsuCorpReservations.IsuCorpReservations.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ReservationDto> getAll() {
        return reservationService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Reservation reservation = reservationService.findById(id);
        if (reservation == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reservation does not exist!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservation);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody final Reservation reservation) {
        try {
            reservationService.save(reservation);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> create(@PathVariable Long id, @RequestBody final Reservation reservation) {
        try {
            reservationService.update(id, reservation);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update reservation!");
        }
    }

}
