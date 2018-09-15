package IsuCorpReservations.IsuCorpReservations.dto;

import IsuCorpReservations.IsuCorpReservations.model.Reservation;
import lombok.Data;
import org.jtransfo.DomainClass;

@Data
@DomainClass(domainClass = Reservation.class)
public class ReservationDto {

    private Long id;

    private String dateOfCreation;

    private int ranking;

    private boolean favorite;

    private String htmlContent;

    private ContactDto contact;

    public ReservationDto() { }
}
