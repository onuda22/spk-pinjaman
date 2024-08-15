package mini_project.spk.repository;

import jakarta.transaction.Transactional;
import mini_project.spk.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Integer> {

    /*
     * Get All Loan Data
     */
    @Query(value = "SELECT * FROM Loans", nativeQuery = true)
    List<Loan> findLoanAll();

    /*
     * Get Loan Data by Id
     */
    @Query(value = "SELECT * FROM Loans WHERE id = :id", nativeQuery = true)
    Loan findLoanById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM loans WHERE id = :loanId", nativeQuery = true)
    Optional<Loan> findLoanByIdOpt(@Param("loanId") Integer loanId);

    @Query(value = "SELECT * FROM loans ORDER BY loan_to_income_ratio DESC LIMIT 1", nativeQuery = true)
    Loan findLoanByMaxLoanIncomeRatio();

    @Query(value = "SELECT * FROM loans ORDER BY collateral_value DESC LIMIT 1", nativeQuery = true)
    Loan findLoanByMaxCollateralValue();

    @Query(value = "SELECT * FROM loans ORDER BY loan_term DESC LIMIT 1", nativeQuery = true)
    Loan findLoanByMaxLoanTerm();
    /*
     * Create Loan Data
     */
    @Transactional
    @Query(value = "INSERT INTO Loans(customer_id, loan_amount, loan_term, collateral_type, collateral_value, , loan_to_income_ratio)" +
            "VALUES (:customer_id, :loan_amount, :loan_term, :collateral_type, :collateral_value, :loan_to_income_ratio) RETURNING *", nativeQuery = true)
    Loan saveLoan(@Param("customer_id") Integer customer_id,
                  @Param("loan_amount") BigDecimal loan_amount,
                  @Param("loan_term") Integer loan_term,
                  @Param("collateral_type") String collateral_type,
                  @Param("collateral_value") BigDecimal collateral_value,
                  @Param("loan_to_income_ratio") BigDecimal loan_to_income_ratio
    );

    /*
     * Update Loan Data
     */
    @Transactional
    @Query(value = "UPDATE Loans SET customer_id = :customer_id, loan_amount = :loan_amount, loan_term = :loan_term, collateral_type = :collateral_type, collateral_value = :collateral_value, loan_to_income_ratio = :loan_to_income_ratio " +
            "WHERE id = :id RETURNING *", nativeQuery = true)
    Loan updateLoan(@Param("id") Integer id,
                    @Param("customer_id") Integer customer_id,
                    @Param("loan_amount") BigDecimal loan_amount,
                    @Param("loan_term") Integer loan_term,
                    @Param("collateral_type") String collateral_type,
                    @Param("collateral_value") BigDecimal collateral_value,
                    @Param("loan_to_income_ratio") BigDecimal loan_to_income_ratio
    );

    /*
     * Delete Loan Data
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Loan WHERE id = :id", nativeQuery = true)
    void deleteLoan(@Param("id") Integer id);
}
