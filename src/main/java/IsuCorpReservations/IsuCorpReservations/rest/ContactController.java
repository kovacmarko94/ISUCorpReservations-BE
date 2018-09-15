package IsuCorpReservations.IsuCorpReservations.rest;

import IsuCorpReservations.IsuCorpReservations.model.Contact;
import IsuCorpReservations.IsuCorpReservations.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<Contact> getAll() {
        return contactService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody final Contact contact) {
        try {
            contactService.save(contact);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EntityExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save contact!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> create(@PathVariable Long id, @RequestBody final Contact contact) {
        try {
            contactService.upadte(id, contact);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update contact!");
        }
    }
}
