package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final CustomerRepository repository;

    private final CustomerMongoRepository customerMongoRepository;

    public Application(CustomerRepository repository, CustomerMongoRepository customerMongoRepository) {
        this.repository = repository;
        this.customerMongoRepository = customerMongoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        Customer aliceCustomer = new Customer("Alice", "Smith");
        Customer bobCustomer = new Customer("Bob", "Smith");

        customerMongoRepository.save(aliceCustomer);
        customerMongoRepository.save(bobCustomer);

        aliceCustomer.setFirstName("Jane");
        repository.update(aliceCustomer);

        aliceCustomer.setLastName("Black");
        repository.update(aliceCustomer);

        customerMongoRepository.delete(bobCustomer);

        repository.delete(aliceCustomer);

    }
}
