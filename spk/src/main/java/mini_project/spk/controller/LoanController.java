package mini_project.spk.controller;

import lombok.RequiredArgsConstructor;
import mini_project.spk.dto.LoanDTO;
import mini_project.spk.model.Loan;
import mini_project.spk.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @GetMapping("/loans-show")
    public List<Loan> getAll(){
        return loanService.getAll();
    }

    @GetMapping("/loans-show/{id}")
    public Loan getById(@PathVariable Integer id){
        return loanService.getById(id);
    }

    @PostMapping("/loans-create")
    public Loan create(@RequestBody LoanDTO request){
        return loanService.create(request);
    }

    @PutMapping("/loans-edit/{id}")
    public Loan updateById(@PathVariable Integer id, @RequestBody LoanDTO request){
        return loanService.updateById(id, request);
    }

    @DeleteMapping("/loans-delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        loanService.deleteById(id);
    }
}
