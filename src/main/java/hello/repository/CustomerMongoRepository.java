package hello.repository;

import hello.repository.entity.Customer;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

@JaversSpringDataAuditable
public interface CustomerMongoRepository extends MongoRepository<Customer, String> {


}
