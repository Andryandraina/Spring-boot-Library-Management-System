package com.knf.dev.librarymanagementsystem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.knf.dev.librarymanagementsystem.entity.Author;
import com.knf.dev.librarymanagementsystem.entity.Book;
import com.knf.dev.librarymanagementsystem.entity.Category;
import com.knf.dev.librarymanagementsystem.entity.Publisher;
import com.knf.dev.librarymanagementsystem.entity.Role;
import com.knf.dev.librarymanagementsystem.entity.User;
import com.knf.dev.librarymanagementsystem.repository.UserRepository;
import com.knf.dev.librarymanagementsystem.service.BookService;

@SpringBootApplication
public class Application {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate() {
		return (args) -> {

			var book = new Book("BK1", "Jamais Plus ~ Collen Hoover ", "B1X", "This is a romance ");
			book.addAuthors(new Author("Martin", "description1"));
			book.addCategories(new Category("Roman "));
			book.addPublishers(new Publisher("publisher1 "));
			bookService.createBook(book);

			var book1 = new Book("BK2", " Coleen Hoover ~ us ", "B2X", "This a Thriller ");
			book1.addAuthors(new Author("Max", " description2"));
			book1.addCategories(new Category("Thriller"));
			book1.addPublishers(new Publisher("publisher2"));
			bookService.createBook(book1);

			var book2 = new Book("BK3", "Miracle Morning ", "B3X", " This is a Personal Development ");
			book2.addAuthors(new Author(" Huges & Milles", " description3"));
			book2.addCategories(new Category("Dev Perso"));
			book2.addPublishers(new Publisher("publisher3"));
			bookService.createBook(book2);

			var user = new User("admin", "admin", "admin@admin.com", passwordEncoder.encode("@123"),
					Arrays.asList(new Role("ROLE_ADMIN")));
			userRepository.save(user);

		};
	}
}
