
package com.contact;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactConfig {

    @SuppressWarnings("null")

    @Bean
    CommandLineRunner commandLineRunner(ContactRepository contactRepository) {
        return args -> {
            ContactDetails maurizia = new ContactDetails(

                    "Maurizia Ingry",
                    486797233458L,
                    "mingry0@paypal.com",
                    "32528 Pankratz Plaza");
            ContactDetails david = new ContactDetails(
                    "David Peter",
                    1111111111L,
                    "mdbjwwkdkekwjw0@paypal.com",
                    "32528 Pankratz ghwggw");
            ContactDetails gift = new ContactDetails(
                    "Gift Peter",
                    412211458L,
                    "gift2Y@YAHOO.COM",
                    "Waec Estate");

            contactRepository.saveAll(List.of(maurizia, david, gift));
        };
    }
}
