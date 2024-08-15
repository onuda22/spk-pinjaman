package mini_project.spk.repository;

import jakarta.transaction.Transactional;
import mini_project.spk.model.Weight;
import mini_project.spk.model.enums.RiskLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WeightRepo extends JpaRepository<Weight, Integer> {

    /*
     * Get All Weight Data
     */
    @Query(value = "SELECT * FROM Weight", nativeQuery = true)
    List<Weight> findWeightAll();

    /*
     * Get Weight Data by Id
     */
    @Query(value = "SELECT * FROM Weight WHERE id = :id", nativeQuery = true)
    Weight findWeightById(@Param("id") Integer id);

    /*
     * Create Weight Data
     */
    @Transactional
    @Query(value = "INSERT INTO Weight(variable_name, weight)" +
            "VALUES (:variable_name, :weight) RETURNING *", nativeQuery = true)
    Weight saveWeight(@Param("variable_name") String variable_name,
                      @Param("weight") BigDecimal weight
    );

    /*
     * Update Weight Data
     */
    @Transactional
    @Query(value = "UPDATE Weight SET variable_name = :variable_name, weight = :weight " +
            "WHERE id = :id RETURNING *", nativeQuery = true)
    Weight updateWeight(@Param("id") Integer id,
                        @Param("variable_name") String variable_name,
                        @Param("weight") BigDecimal weight
    );

    /*
     * Delete Weight Data
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Weight WHERE id = :id", nativeQuery = true)
    void deleteWeight(@Param("id") Integer id);
}
