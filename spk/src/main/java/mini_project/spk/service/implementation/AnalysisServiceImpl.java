package mini_project.spk.service.implementation;

import lombok.RequiredArgsConstructor;
import mini_project.spk.dto.AnalysisDTO;
import mini_project.spk.dto.AnalysisResultDTO;
import mini_project.spk.model.Analysis;
import mini_project.spk.model.Customer;
import mini_project.spk.model.Loan;
import mini_project.spk.model.enums.CharacterRating;
import mini_project.spk.model.enums.RiskLevel;
import mini_project.spk.repository.AnalysisRepo;
import mini_project.spk.repository.CustomerRepo;
import mini_project.spk.repository.LoanRepo;
import mini_project.spk.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Autowired
    private AnalysisRepo analysisRepository;

    @Override
    public List<Analysis> getAll() {
        return analysisRepo.findAnalysisAll();
    }

    @Override
    public Analysis getById(Integer id) {
        return analysisRepo.findAnalysisById(id);
    }

    @Override
    public void deleteById(Integer id) {
        analysisRepo.deleteAnalysis(id);
    }

    @Override
    public AnalysisResultDTO analyzeLoan(Integer loanId) {
        Optional<Loan> loanOpt = loanRepository.findLoanByIdOpt(loanId);
        if (loanOpt.isEmpty()) {
            throw new RuntimeException("Loan not found");
        }
        Loan loan = loanOpt.get();

        Optional<Customer> customerOpt = customerRepository.findCustomerByIdOpt(loan.getCustomer().getId());
        if (customerOpt.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }
        Customer customer = customerOpt.get();

        // Perform analysis
        BigDecimal eligibilityScore = calculateEligibilityScore(loan, customer);
        String riskLevel = determineRiskLevel(eligibilityScore);
        String riskEnum = "";

        if (riskLevel.equals("Low Risk")){
            riskEnum = String.valueOf(RiskLevel.LOW);
        } else if (riskLevel.equals("Medium Risk")) {
            riskEnum = String.valueOf(RiskLevel.MEDIUM);
        } else if (riskLevel.equals("High Risk")) {
            riskEnum = String.valueOf(RiskLevel.HIGH);
        }

        // Create and return analysis result
        AnalysisResultDTO result = new AnalysisResultDTO();
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

        // Save to Analysis Table
        analysisRepo.saveAnalysis(loanId, eligibilityScore, riskEnum);

        return result;
    }

    public BigDecimal calculateEligibilityScore(Loan loan, Customer customer) {

        BigDecimal score = BigDecimal.ZERO;

        // Set Weight Score
        BigDecimal characterRatingWeight = new BigDecimal(20);
        BigDecimal incomeWeight = new BigDecimal(20);
        BigDecimal creditScoreWeight = new BigDecimal(20);
        BigDecimal loanToIncomeRatioWeight = new BigDecimal(15);
        BigDecimal collateralValueWeight = new BigDecimal(15);
        BigDecimal ageWeight = new BigDecimal(10);
        BigDecimal loanTermWeight = new BigDecimal(10);

        // Max Data From All Required Data
        BigDecimal maxCharacterRating = new BigDecimal(100);
        var maxIncome = customerRepository.findCustomerByMaxIncome().getIncome();
        var maxCreditScore = 850;
        var maxLoanToIncomeRatio = loanRepository.findLoanByMaxLoanIncomeRatio().getLoanToIncomeRatio();
        var maxCollateralValue = loanRepository.findLoanByMaxCollateralValue().getCollateralValue();
        var maxAge = customerRepository.findCustomerByAge().getAge();
        var maxLoanTerm = loanRepository.findLoanByMaxLoanTerm().getLoanTerm();

        // Data Normalization
        BigDecimal cr_normal = characterRatingToScore(customer.getCharacterRating()).divide(maxCharacterRating, 2, RoundingMode.CEILING);
        BigDecimal i_normal = customer.getIncome().divide(maxIncome, 2, RoundingMode.CEILING);
        BigDecimal cs_normal = BigDecimal.valueOf(customer.getCreditScore()/maxCreditScore);
        BigDecimal ltir_normal = loan.getLoanToIncomeRatio().divide(maxLoanToIncomeRatio, 2, RoundingMode.CEILING);
        BigDecimal cv_normal = loan.getCollateralValue().divide(maxCollateralValue,2, RoundingMode.CEILING);
        BigDecimal a_normal = BigDecimal.valueOf(customer.getAge()/maxAge);
        BigDecimal lt_normal = BigDecimal.valueOf(loan.getLoanTerm()/maxLoanTerm);


        // Calculate Score
        score = score.add(creditScoreWeight.multiply(cs_normal));
        score = score.add(incomeWeight.multiply(i_normal));
        score = score.add(loanToIncomeRatioWeight.multiply(ltir_normal));
        score = score.add(collateralValueWeight.multiply(cv_normal));
        score = score.add(characterRatingWeight.multiply(cr_normal));
        score = score.add(ageWeight.multiply(a_normal));
        score = score.add(loanTermWeight.multiply(lt_normal));

        return score;
    }

    private BigDecimal characterRatingToScore(CharacterRating characterRating) {
        return switch (characterRating) {
            case AWFUL -> new BigDecimal("20");
            case BAD -> new BigDecimal("40");
            case ENOUGH -> new BigDecimal("60");
            case GOOD -> new BigDecimal("80");
            case EXCELLENT -> new BigDecimal("100");
        };
    }

    private String determineRiskLevel(BigDecimal eligibilityScore) {
        if (eligibilityScore.compareTo(LOW_RISK_THRESHOLD) >= 0) {
            return "Low Risk";
        } else if (eligibilityScore.compareTo(MEDIUM_RISK_THRESHOLD) >= 0) {
            return "Medium Risk";
        } else {
            return "High Risk";
        }
    }

}
