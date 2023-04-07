package hello.service;

import hello.controller.CustomerRequest;
import hello.controller.CustomerResponse;
import hello.repository.CustomerRepository;
import hello.repository.CustomerRepositoryImpl;
import hello.repository.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerRepositoryImpl customerRepositoryImpl;

    public CustomerService(CustomerRepository customerRepository, CustomerRepositoryImpl customerRepositoryImpl) {
        this.customerRepository = customerRepository;
        this.customerRepositoryImpl = customerRepositoryImpl;
    }

    public void save(CustomerRequest requestBody) {
        Customer customer = new Customer(requestBody.firstName(), requestBody.lastName());
        customerRepository.save(customer);
    }

    public List<CustomerResponse> findByLastName(String lastName) {
        return customerRepository.findByLastName(lastName)
                .stream()
                .map(customer -> new CustomerResponse(customer.getFirstName(), customer.getLastName()))
                .toList();

    }

    public void updateFirstName(CustomerRequest requestBody) {
        Customer customer = customerRepository.findByLastName(requestBody.lastName()).stream().findFirst().orElseThrow(NoSuchElementException::new);
        customer.setFirstName(requestBody.firstName());
        customerRepositoryImpl.update(customer);
    }

    public void delete(CustomerRequest requestBody) {
        Customer customer = new Customer(requestBody.firstName(), requestBody.lastName());
        customer.setId("not null");
        customerRepository.delete(customer);
    }


    public List<CustomerResponse> findAll() {
        return customerRepositoryImpl.findAll()
                .stream()
                .map(customer -> new CustomerResponse(customer.getFirstName(), customer.getLastName()))
                .toList();

    }

}
