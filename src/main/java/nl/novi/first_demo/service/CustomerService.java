package nl.novi.first_demo.service;


import nl.novi.first_demo.exeption.RecordNotFoundException;
import nl.novi.first_demo.model.Customer;
import nl.novi.first_demo.model.Supplier;
import nl.novi.first_demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private static List<Customer> customers = new ArrayList<>();

    @Autowired
    private CustomerRepository customerRepository;

    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()){
            return customer.get();
        }
        else {
            throw new RecordNotFoundException("Customer with id " + id + " not found.");
        }
    }

    public Long addCustomer(Customer customer) {
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer.getId();
    }


    public void deleteCustomer(long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("Customer with id " + id + " not found.");
        }
    }

    public void updateCustomer(long id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()){
            Customer customerInDb = optionalCustomer.get();
            customerInDb.setName(customer.getName());
            customerInDb.setAddress(customer.getAddress());
            customerInDb.setEmail(customer.getEmail());
            customerRepository.save(customerInDb);
        }
        else {
            throw new RecordNotFoundException("Customer with id " + id + " not found.");
        }

    }

}