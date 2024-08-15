package mini_project.spk.service.implementation;

import lombok.RequiredArgsConstructor;
import mini_project.spk.model.Customer;
import mini_project.spk.repository.CustomerRepo;
import mini_project.spk.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;

    @Override
    public List<Customer> getAll() {
        return customerRepo.findCustomerAll();
    }

    @Override
    public Customer getById(Integer id) {
        var customer = customerRepo.findCustomerById(id);
        if(customer == null){
            throw new RuntimeException("Customer Not Found");
        }
        return customer;
    }

    @Override
    public Customer create(Customer newCustomer) {
        return customerRepo.saveCustomer(newCustomer.getName(),
                newCustomer.getAge(),
                newCustomer.getIncome(),
                newCustomer.getCreditScore(),
                String.valueOf(newCustomer.getCharacterRating()));
    }

    @Override
    public Customer updateById(Integer id, Customer updateCustomer) {
        return customerRepo.updateCustomer(id,
                updateCustomer.getName(),
                updateCustomer.getAge(),
                updateCustomer.getIncome(),
                updateCustomer.getCreditScore(),
                String.valueOf(updateCustomer.getCharacterRating()));
    }

    @Override
    public void deleteById(Integer id) {
        customerRepo.deleteCustomer(id);
    }
}
