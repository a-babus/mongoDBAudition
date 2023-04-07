package hello.repository;

import hello.repository.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);

    void update(Customer customer);

    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);

    Customer findByFirstAndLastName(String firstName, String lastName);

    void deleteAll();

    void delete(Customer customer);

    List<Customer> findAll();

}
