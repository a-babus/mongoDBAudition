package hello.controller;

import hello.service.CustomerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void createNewCustomer(@RequestBody CustomerRequest customerRequest) {
        customerService.save(customerRequest);
    }

    @GetMapping
    public List<CustomerResponse> getCustomerByLastName(@RequestParam String lastName) {
        return customerService.findByLastName(lastName);
    }

    @PutMapping
    public void updateFirstName(@RequestBody CustomerRequest customerRequest) {
        customerService.updateFirstName(customerRequest);
    }

    @DeleteMapping
    public void deleteCustomer(@RequestBody CustomerRequest customerRequest) {
        customerService.delete(customerRequest);
    }
}
