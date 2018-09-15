package IsuCorpReservations.IsuCorpReservations.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Contact {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int type;

    private String phoneNumber;

    @Column(nullable = false)
    private String dateOfBirth;

    public Contact(String name, int type, String dateOfBirth) {
        this.name = name;
        this.type = type;
        this.dateOfBirth = dateOfBirth;
    }
}
