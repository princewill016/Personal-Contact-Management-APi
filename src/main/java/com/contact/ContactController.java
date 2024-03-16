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
@RequestMapping()
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

    @GetMapping(path = "{contactDetailsId}") // Change the method name to match the endpoint
    public ContactDetails getContactDetail(@PathVariable("contactDetailsId") Long contactDetailsId) {
        return contactService.getContactDetail(contactDetailsId).orElseThrow(
                () -> new UnsupportedOperationException("There is no Contact with id " + contactDetailsId));
    }

    @PostMapping("/contactAdd")
    public ContactDetails addContact(@RequestBody ContactDetails contactDetail) {
        return contactService.addContact(contactDetail);
    }

    @PostMapping("/contactAdds")
    public List<ContactDetails> addContacts(@RequestBody List<ContactDetails> contactDetails) {
        contactService.addContacts(contactDetails);
        return contactDetails;
    }

    @DeleteMapping(path = "{ContactDetailsId}")
    public void deleteStudent(@PathVariable("ContactDetailsId") Long ContactDetailsId) {
        contactService.deleteContact(ContactDetailsId);
    }

    @PutMapping(path = "{ContactDetailsId}")
    public void updateContact(@PathVariable("ContactDetailsId") Long ContactDetailsId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        contactService.updateContact(ContactDetailsId, name, email);
    }
}
