package mini_project.spk.service;

import mini_project.spk.dto.LoanDTO;
import mini_project.spk.model.Loan;

import java.util.List;

public interface LoanService {

    List<Loan> getAll();
    Loan getById(Integer id);
    Loan create(LoanDTO newLoan);
    Loan updateById(Integer id, LoanDTO updateLoan);
    void deleteById(Integer id);
}
