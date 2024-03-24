package com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication

public class ContactApplication {
	// @Bean
	// Docket api() {
	// return new Docket(DocumentationType.OAS_30)
	// .select()
	// .apis(RequestHandlerSelectors.basePackage("com.contact"))
	// .paths(PathSelectors.any())
	// .build()
	// .apiInfo(apiInfo());
	// }

	// private ApiInfo apiInfo() {
	// return new ApiInfo("Contact Management API Documentation",
	// "This Api Documentation doecuments for Contact Management", "Version 1.0",
	// "Free For ALL",
	// new springfox.documentation.service.Contact("Peter David",
	// "Princewill016@gmail.com",
	// "Princewill016@gmail.com"),
	// null, null, null);

	public static void main(String[] args) {
		SpringApplication.run(ContactApplication.class, args);
	}
}
