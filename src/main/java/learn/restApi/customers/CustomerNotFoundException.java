package learn.restApi.customers;

public class CustomerNotFoundException extends RuntimeException {

  CustomerNotFoundException(Long id) {
    super(String.format("Customer with id: %s doesn't exist", id));
  }

}
