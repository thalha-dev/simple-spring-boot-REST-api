package learn.restApi.customers;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CustomerRepository
 */

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
