package hello.repository;

import hello.repository.entity.Customer;
import org.javers.spring.annotation.JaversAuditable;
import org.javers.spring.annotation.JaversAuditableDelete;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";
    private final MongoTemplate mongoTemplate;

    public CustomerRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @JaversAuditable
    public Customer save(Customer customer) {
        return mongoTemplate.save(customer);
    }

    @JaversAuditable
    public void update(Customer customer) {
        mongoTemplate.save(customer);
    }

    public Customer findByFirstName(String firstName) {
        Query query = new Query();
        query.addCriteria(Criteria.where(FIRST_NAME).is(firstName));
        return mongoTemplate.find(query, Customer.class).get(0);
    }

    public List<Customer> findByLastName(String lastName) {
        Query query = new Query();
        query.addCriteria(Criteria.where(LAST_NAME).is(lastName));
        return mongoTemplate.find(query, Customer.class);
    }

    public Customer findByFirstAndLastName(String firstName, String lastName) {
        Query query = new Query();
        query.addCriteria(Criteria.where(FIRST_NAME).is(firstName));
        query.addCriteria(Criteria.where(LAST_NAME).is(lastName));
        return mongoTemplate.find(query, Customer.class).stream().findFirst().orElseThrow(NoSuchElementException::new);
    }

    public void deleteAll() {
        mongoTemplate.remove(new Query(), "customer");
    }

    @JaversAuditableDelete
    public void delete(Customer customer) {
        Query query = new Query();
        query.addCriteria(Criteria.where(FIRST_NAME).is(customer.getFirstName()));
        query.addCriteria(Criteria.where(LAST_NAME).is(customer.getLastName()));
        mongoTemplate.remove(query, Customer.class);
    }

    public List<Customer> findAll() {
        return mongoTemplate.findAll(Customer.class);
    }
}
