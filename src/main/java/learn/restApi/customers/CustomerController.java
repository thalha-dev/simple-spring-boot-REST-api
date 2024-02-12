package learn.restApi.customers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  private CustomerRepository repository;

  public CustomerController(CustomerRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/customer")
  List<Customer> all() {
    return repository.findAll();
  }

  @GetMapping("/customer/{id}")
  Customer get(@PathVariable Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new CustomerNotFoundException(id));
  }

  @PostMapping("/customer")
  Customer save(@RequestBody Customer newCustomer) {
    return repository.save(newCustomer);
  }

  @DeleteMapping("/customer/{id}")
  void delete(@PathVariable Long id) {
    repository.deleteById(id);
  }

  @PutMapping("/customer/{id}")
  Customer update(@PathVariable Long id, @RequestBody Customer customerBody) {
    return repository.findById(id).map(customer -> {
      customer.setEmail(customerBody.getEmail());
      customer.setName(customerBody.getName());
      return repository.save(customer);
    }).orElseGet(() -> {
      customerBody.setId(id);
      return repository.save(customerBody);
    });
  }

}
