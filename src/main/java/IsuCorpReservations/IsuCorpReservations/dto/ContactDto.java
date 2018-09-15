package IsuCorpReservations.IsuCorpReservations.dto;

import IsuCorpReservations.IsuCorpReservations.model.Contact;
import lombok.Data;
import org.jtransfo.DomainClass;
import org.jtransfo.NotMapped;

import java.util.List;

@Data
@DomainClass(domainClass = Contact.class)
public class ContactDto {

    private Long id;

    private String name;

    private int type;

    private String phoneNumber;

    private String dateOfBirth;

    @NotMapped
    private List<ReservationDto> reservations;

    public ContactDto() { }

    public ContactDto(String name, int type, String dateOfBirth) {
        this.name = name;
        this.type = type;
        this.dateOfBirth = dateOfBirth;
    }
}
