package IsuCorpReservations.IsuCorpReservations.service;

import IsuCorpReservations.IsuCorpReservations.model.Contact;
import IsuCorpReservations.IsuCorpReservations.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact save(Contact contact) {
        Contact sameContact = contactRepository.findByName(contact.getName());
        if (sameContact != null) {
            throw new EntityExistsException("Contact already exist!");
        }
        return contactRepository.save(contact);
    }

    public List<Contact> findAll() {
        return  contactRepository.findAll();
    }

    public Contact findById(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.get();
    }

    public Contact upadte(Long id, Contact newContact) throws EntityNotFoundException {
        Contact contact = findById(id);
        if (contact == null) {
            throw new EntityNotFoundException(String.format("Contact with %d does not exist!", id));
        }
        contact.setName(newContact.getName());
        contact.setType(newContact.getType());
        contact.setPhoneNumber(newContact.getPhoneNumber());
        contact.setDateOfBirth(newContact.getDateOfBirth());
        return contactRepository.save(contact);
    }
}
