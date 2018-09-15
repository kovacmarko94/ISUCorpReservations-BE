package IsuCorpReservations.IsuCorpReservations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Reservation {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String dateOfCreation;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int ranking;

    @Column(columnDefinition = "TINYINT DEFAULT 0")
    private boolean favorite;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String htmlContent;
    
    @ManyToOne
    private Contact contact;

    public Reservation() {

    }
}
