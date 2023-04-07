package hello;

import org.javers.spring.annotation.JaversAuditable;
import org.javers.spring.annotation.JaversAuditableDelete;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final MongoTemplate mongoTemplate;

    public CustomerRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Customer save(Customer customer) {
        return mongoTemplate.save(customer);
    }

    @JaversAuditable
    public void update(Customer customer) {
        mongoTemplate.save(customer);
    }

    public Customer findByFirstName(String firstName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        return mongoTemplate.find(query, Customer.class).get(0);
    }

    public List<Customer> findByLastName(String lastName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("lastName").is(lastName));
        return mongoTemplate.find(query, Customer.class);
    }

    public void deleteAll() {
        mongoTemplate.remove(new Query(), "customer");
    }

    @JaversAuditableDelete
    public void delete(Customer customer) {
        mongoTemplate.remove(customer);
    }

    public List<Customer> findAll() {
        return mongoTemplate.findAll(Customer.class);
    }
}
