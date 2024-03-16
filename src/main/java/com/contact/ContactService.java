package com.contact;

import java.util.*;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactDetails> getContactDetails() {
        return contactRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<ContactDetails> getContactDetail(Long ContactDetailsId) {
        boolean exists = contactRepository.existsById(ContactDetailsId);
        if (!exists) {
            throw new UnsupportedOperationException("There is no Contact with id" + ContactDetailsId);
        }
        return contactRepository.findContactById(ContactDetailsId);
    }

    public void addContact(ContactDetails contactDetails) {
        // if it doesnt work, edit the 'c' in contactdetails to uppercase.
        Optional<ContactDetails> contactOptional = contactRepository.findContactByEmail(contactDetails.getEmail());
        if (contactOptional.isPresent()) {
            throw new IllegalStateException("email exists already");
        } else {
            contactRepository.save(contactDetails);
        }
    }

    @SuppressWarnings("null")
    public void addContacts(List<ContactDetails> contactDetails) {
        contactRepository.saveAll(contactDetails);
    }

    @SuppressWarnings("null")
    public void deleteStudent(Long contactDetailsId) {
        boolean exists = contactRepository.existsById(contactDetailsId);
        if (!exists) {
            throw new IllegalStateException("There is no Contact with id" + contactDetailsId);
        } else
            contactRepository.deleteById(contactDetailsId);
    }

    @SuppressWarnings("null")
    @Transactional
    public void updateContact(Long ContactDetailsId, String name, String email) {
        ContactDetails contact = contactRepository.findById(ContactDetailsId)
                .orElseThrow(() -> new IllegalStateException("Contact with id " + ContactDetailsId + "not found"));

        if (name != null && name.length() > 0 && !Objects.equals(contact.getName(), name)){
            contact.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(contact.getEmail(), email)) {
            contact.setEmail(email);
        }

    }

}
