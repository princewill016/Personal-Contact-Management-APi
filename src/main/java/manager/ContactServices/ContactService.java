package manager.ContactServices;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import manager.ContactModel.ContactDetails;

@Service
public interface ContactService {

    Page<ContactDetails> getContactDetails(Pageable pageable);

    Optional<ContactDetails> getContactDetail(Long contactDetailsId);

    Optional<ContactDetails> getContactDetailByName(String contactDetailsName);

    ContactDetails addContact(ContactDetails contactDetails);

  void addContacts(List<ContactDetails> contactDetails);

    void deleteContact(Long contactDetailsId);

    void updateContact(Long contactDetailsId, String name, String email);

}