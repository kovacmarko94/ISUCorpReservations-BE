package IsuCorpReservations.IsuCorpReservations.repository;

import IsuCorpReservations.IsuCorpReservations.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByName(String name);
}
