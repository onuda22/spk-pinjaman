package mini_project.spk.repository;

import jakarta.transaction.Transactional;
import mini_project.spk.model.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnalysisRepo extends JpaRepository<Analysis, Integer> {

    /*
     * Get All Analysis Data
     */
    @Query(value = "SELECT * FROM Analysis", nativeQuery = true)
    List<Analysis> findAnalysisAll();

    /*
     * Get Analysis Data by Id
     */
    @Query(value = "SELECT * FROM Analysis WHERE id = :id", nativeQuery = true)
    Analysis findAnalysisById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM analysis WHERE loan_id = :loanId", nativeQuery = true)
    Optional<Analysis> findAnalysisByLoanId(@Param("loanId") Integer loanId);

    /*
     * Create Analysis Data
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Analysis(loan_id, eligibility_score, risk_level)" +
            "VALUES (:loan_id, :eligibility_score, :risk_level)", nativeQuery = true)
    void saveAnalysis(@Param("loan_id") Integer loan_id,
                      @Param("eligibility_score") BigDecimal eligibility_score,
                      @Param("risk_level") String risk_level
    );

    /*
     * Update Analysis Data
     */
    @Transactional
    @Query(value = "UPDATE Analysis SET loan_id = :loan_id eligibility_score = :eligibility_score, risk_level = :risk_level " +
            "WHERE id = :id RETURNING *", nativeQuery = true)
    Analysis updateAnalysis(@Param("id") Integer id,
                            @Param("loan_id") Integer loan_id,
                            @Param("eligibility_score") BigDecimal eligibility_score,
                            @Param("risk_level") String risk_level
    );

    /*
     * Delete Analysis Data
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Analysis WHERE id = :id", nativeQuery = true)
    void deleteAnalysis(@Param("id") Integer id);
}
