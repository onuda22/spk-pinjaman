package mini_project.spk.controller;

import lombok.RequiredArgsConstructor;
import mini_project.spk.model.Customer;
import mini_project.spk.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/customers-show")
    public List<Customer> getAll(){
        return customerService.getAll();
    }

    @GetMapping("/customers-show/{id}")
    public Customer getById(@PathVariable Integer id){
        return customerService.getById(id);
    }

    @PostMapping("/customers-create")
    public Customer create(@RequestBody Customer request){
        return customerService.create(request);
    }

    @PutMapping("/customers-edit/{id}")
    public Customer updateById(@PathVariable Integer id,@RequestBody Customer request){
        return customerService.updateById(id, request);
    }

    @DeleteMapping("/customers-delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        customerService.deleteById(id);
    }
}
