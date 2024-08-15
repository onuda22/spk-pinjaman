package mini_project.spk.service.implementation;

import lombok.RequiredArgsConstructor;
import mini_project.spk.dto.AnalysisDTO;
import mini_project.spk.dto.AnalysisResultDTO;
import mini_project.spk.model.Analysis;
import mini_project.spk.model.Customer;
import mini_project.spk.model.Loan;
import mini_project.spk.repository.AnalysisRepo;
import mini_project.spk.repository.CustomerRepo;
import mini_project.spk.repository.LoanRepo;
import mini_project.spk.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {
    private final AnalysisRepo analysisRepo;

    private static final BigDecimal LOW_RISK_THRESHOLD = new BigDecimal("70");
    private static final BigDecimal MEDIUM_RISK_THRESHOLD = new BigDecimal("50");

    @Autowired
    private CustomerRepo customerRepository;

    @Autowired
    private LoanRepo loanRepository;

    @Override
    public List<Analysis> getAll() {
        return analysisRepo.findAnalysisAll();
    }

    @Override
    public Analysis getById(Integer id) {
        return analysisRepo.findAnalysisById(id);
    }

    @Override
    public Analysis create(AnalysisDTO request) {
        return analysisRepo.saveAnalysis();
    }

    @Override
    public Analysis updateById(Integer id, AnalysisDTO updateReq) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    public AnalysisResultDTO analyzeLoan(Integer loanId) {
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if (loanOpt.isEmpty()) {
            throw new RuntimeException("Loan not found");
        }
        Loan loan = loanOpt.get();

        Optional<Customer> customerOpt = customerRepository.findById(loan.getCustomer().getId());
        if (customerOpt.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }
        Customer customer = customerOpt.get();

        // Perform analysis
        BigDecimal eligibilityScore = calculateEligibilityScore(loan, customer);
        String riskLevel = determineRiskLevel(eligibilityScore);

        // Create and return analysis result
        LoanAnalysisResultDto result = new LoanAnalysisResultDto();
        result.setLoanId(loanId);
        result.setName(customer.getName());
        result.setLoanAmount(loan.getLoanAmount());
        result.setIncome(customer.getIncome());
        result.setCreditScore(customer.getCreditScore());
        result.setAge(customer.getAge());
        result.setLoanTerm(loan.getLoanTerm());
        result.setLoanToIncomeRatio(loan.getLoanToIncomeRatio());
        result.setCollateralValue(loan.getCollateralValue());
        result.setEligibilityScore(eligibilityScore);
        result.setRiskLevel(riskLevel);

        return result;
    }

}
