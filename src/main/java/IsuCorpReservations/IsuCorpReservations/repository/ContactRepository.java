package IsuCorpReservations.IsuCorpReservations.repository;

import IsuCorpReservations.IsuCorpReservations.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact save(Contact contact);
    ArrayList<Contact> findAll();
    Contact findByName(String name);
    Optional<Contact> findById(Long id);
}
