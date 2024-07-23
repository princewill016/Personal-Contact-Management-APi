package manager.Controllers;

import java.util.List;

import manager.ContactModel.ContactDetails;
import manager.ContactServices.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "api/v1/contact")
@Validated
public class ContactController {
    @Autowired
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;

    }

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @GetMapping()
    public ResponseEntity<List<ContactDetails>> getContactDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        Page<ContactDetails> contacts = contactService.getContactDetails(pageable);

        return ResponseEntity.ok().body(contacts.getContent());
    }

    @GetMapping(path = "{Id}")
    public ContactDetails getContactDetail(@PathVariable("Id") Long Id) {
        return contactService.getContactDetail(Id).orElseThrow(
                () -> new UnsupportedOperationException("There is no Contact with id " +
                        Id));
    }

    @GetMapping(path = "name/{contactDetailsName}")
    public ContactDetails getContactDetailByName(@PathVariable("contactDetailsName") String contactDetailsName) {
        return contactService.getContactDetailByName(contactDetailsName).orElseThrow(
                () -> new UnsupportedOperationException("There is no Contact as " + contactDetailsName
                        + " please check the spelling and try again"));
    }

    @PostMapping("/newContact")
    public ContactDetails addContact(@Valid @RequestBody ContactDetails contactDetail) {
        logger.info("method called for adding one contact");
        return contactService.addContact(contactDetail);
    }

    @PostMapping("/newContacts")
    public void  addContacts(@RequestBody List<ContactDetails> contactDetails) {
        logger.info("adding contacts");
        contactService.addContacts(contactDetails);
        logger.info("all contacts added successfully");
    }

    @DeleteMapping(path = "{Id}")
    public void deleteStudent(@PathVariable("Id") Long Id) {
        logger.info("ready to delete {}", Id);
        contactService.deleteContact(Id);
        logger.info("id deleted {}", Id);
    }

    @PutMapping(path = "{Id}")
    public void updateContact(@PathVariable("Id") Long Id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        logger.info("Updating contact with ID: {}", Id);
        logger.debug("Received parameters: name={}, email={}", name, email);
        contactService.updateContact(Id, name, email);

    }

}