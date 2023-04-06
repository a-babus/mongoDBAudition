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

        repository.save(aliceCustomer);
        repository.save(bobCustomer);

        aliceCustomer.setLastName("Black");
        repository.update(aliceCustomer);

        aliceCustomer.setLastName("Smith");
        customerMongoRepository.save(aliceCustomer);

        customerMongoRepository.delete(aliceCustomer);

        //FixMe: Is not logged with MongoTemplate, to be investigated
//        repository.delete(aliceCustomer);
//        repository.delete(bobCustomer);

    }
}
