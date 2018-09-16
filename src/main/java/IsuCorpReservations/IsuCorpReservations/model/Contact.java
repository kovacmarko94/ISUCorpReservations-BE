package IsuCorpReservations.IsuCorpReservations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "contact", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Contact(String name, int type, String dateOfBirth) {
        this.name = name;
        this.type = type;
        this.dateOfBirth = dateOfBirth;
    }
}
