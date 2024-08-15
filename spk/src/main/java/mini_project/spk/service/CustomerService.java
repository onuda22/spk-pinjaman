package mini_project.spk.service;

import mini_project.spk.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAll();
    Customer getById(Integer id);
    Customer create(Customer newCustomer);
    Customer updateById(Integer id, Customer updateCustomer);
    void deleteById(Integer id);

}
