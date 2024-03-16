package com.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "api/v1/contact")
public class ContactController {
    @Autowired
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping()
    public List<ContactDetails> getContactDetails() {
        return contactService.getContactDetails();
    }

    @GetMapping(path = "api/v1/{ContactDetailsId}")
    public List<ContactDetails> getContactDetail() {
        return contactService.getContactDetails();
    }

    @PostMapping(path = "api/v1/register_contact")
    public void addContact(@RequestBody ContactDetails contactDetail) {
        contactService.addContact(contactDetail);
    }

    @PostMapping(path = "api/v1/register_contacts")
    public List<ContactDetails> addContacts(@RequestBody List<ContactDetails> contactDetails) {
        contactService.addContacts(contactDetails);
        return contactDetails;
    }

    @DeleteMapping(path = "{ContactDetailsId}")
    public void deleteStudent(@PathVariable("ContactDetailsId") Long ContactDetailsId) {
        contactService.deleteStudent(ContactDetailsId);
    }

    @PutMapping("{ContactDetailsId}")
    public void updateContact(@PathVariable("ContactDetailsId") Long ContactDetailsId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        contactService.updateContact(ContactDetailsId, name, email);
    }
}
