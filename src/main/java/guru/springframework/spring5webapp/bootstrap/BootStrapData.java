package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "12345");

        // Adds book to eric and eric to the book
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        // Saving into the H2 Database
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnston");
        Book noEJB = new Book("J2EE", "12345");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        // Saving to H2 database
        authorRepository.save(rod);
        bookRepository.save(noEJB);

        // Publisher creation
        Publisher penguin = new Publisher("Penguin", "NoWhere Street", "Fairbanks", "AK", "99701");

        // Add publisher to repo
        publisherRepository.save(penguin);

        // Console messaging output
        System.out.println("Started in bootstrap");
        System.out.println("Books found: " + bookRepository.count());
        System.out.println("Authors found: " + authorRepository.count());
        System.out.println("Publisher found: " + publisherRepository.count());

    }
}
