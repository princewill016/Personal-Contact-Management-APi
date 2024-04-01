package com.ContactServices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ContactModel.ContactDetails;

@Service
public interface ContactService {

    List<ContactDetails> getContactDetails();

    Optional<ContactDetails> getContactDetail(Long contactDetailsId);

    Optional<ContactDetails> getContactDetailByName(String contactDetailsName);

    ContactDetails addContact(ContactDetails contactDetails);

    List<ContactDetails> addContacts(List<ContactDetails> contactDetails);

    void deleteContact(Long contactDetailsId);

    void updateContact(Long contactDetailsId, String name, String email);

}