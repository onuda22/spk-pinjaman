package mini_project.spk.service.implementation;

import lombok.RequiredArgsConstructor;
import mini_project.spk.dto.LoanDTO;
import mini_project.spk.model.Loan;
import mini_project.spk.repository.LoanRepo;
import mini_project.spk.service.LoanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepo loanRepo;

    @Override
    public List<Loan> getAll() {
        return loanRepo.findLoanAll();
    }

    @Override
    public Loan getById(Integer id) {
        var loan = loanRepo.findLoanById(id);
        if (loan == null){
            throw new RuntimeException("Loan Data Not Found");
        }
        return loan;
    }

    @Override
    public Loan create(LoanDTO newLoan) {
        return loanRepo.saveLoan(
                newLoan.getCustomerId(),
                newLoan.getLoanAmount(),
                newLoan.getLoanTerm(),
                newLoan.getCollateralType(),
                newLoan.getCollateralValue()
        );
    }

    @Override
    public Loan updateById(Integer id, LoanDTO updateLoan) {
        return loanRepo.updateLoan(
                id,
                updateLoan.getCustomerId(),
                updateLoan.getLoanAmount(),
                updateLoan.getLoanTerm(),
                updateLoan.getCollateralType(),
                updateLoan.getCollateralValue()
        );
    }

    @Override
    public void deleteById(Integer id) {
        loanRepo.deleteById(id);
    }
}
