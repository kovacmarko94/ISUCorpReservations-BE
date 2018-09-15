package IsuCorpReservations.IsuCorpReservations.service;

import IsuCorpReservations.IsuCorpReservations.dto.ContactDto;
import IsuCorpReservations.IsuCorpReservations.model.Contact;
import IsuCorpReservations.IsuCorpReservations.repository.ContactRepository;
import org.jtransfo.JTransfo;
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

    @Autowired
    private JTransfo jTransfo;

    public ContactDto save(Contact contact) {
        Contact tempContact = contactRepository.findByName(contact.getName());
        if (tempContact != null) {
            throw new EntityExistsException("Contact already exist!");
        }
        contact = contactRepository.save(contact);
        return jTransfo.convertTo(contact, ContactDto.class);
    }

    public List<ContactDto> findAll() {
        List<Contact> contacts = contactRepository.findAll();
        return jTransfo.convertList(contacts, ContactDto.class);
    }

    public Contact findById(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.get();
    }

    public ContactDto upadte(Long id, Contact newContact) throws EntityNotFoundException {
        Contact contact = findById(id);
        if (contact == null) {
            throw new EntityNotFoundException(String.format("Contact with %d does not exist! Update failed!", id));
        }
        contact.setName(newContact.getName());
        contact.setType(newContact.getType());
        contact.setPhoneNumber(newContact.getPhoneNumber());
        contact.setDateOfBirth(newContact.getDateOfBirth());
        contact = contactRepository.save(contact);
        return jTransfo.convertTo(contact, ContactDto.class);
    }
}
