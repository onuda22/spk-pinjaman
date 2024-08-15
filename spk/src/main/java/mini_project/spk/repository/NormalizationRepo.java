package mini_project.spk.repository;

import jakarta.transaction.Transactional;
import mini_project.spk.model.NormalizationParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface NormalizationRepo extends JpaRepository<NormalizationParameter, Integer> {

    /*
     * Get All Normalization Parameter Data
     */
    @Query(value = "SELECT * FROM Normalization_params", nativeQuery = true)
    List<NormalizationParameter> findNormalizationParameterAll();

    /*
     * Get Normalization Parameter Data by Id
     */
    @Query(value = "SELECT * FROM Normalization_params WHERE id = :id", nativeQuery = true)
    NormalizationParameter findNormalizationParameterById(@Param("id") Integer id);

    /*
     * Create Normalization Parameter Data
     */
    @Transactional
    @Query(value = "INSERT INTO Normalization_params(param_name, min_value, max_value)" +
            "VALUES (:param_name, :min_value, :max_value) RETURNING *", nativeQuery = true)
    NormalizationParameter saveNormalizationParameter(@Param("param_name") String param_name,
                                                      @Param("min_value") BigDecimal min_value,
                                                      @Param("max_value") BigDecimal max_value
    );

    /*
     * Update Normalization Parameter Data
     */
    @Transactional
    @Query(value = "UPDATE Normalization_params SET param_name = :param_name, min_value = :min_value, max_value = :max_value " +
            "WHERE id = :id RETURNING *", nativeQuery = true)
    NormalizationParameter updateNormalizationParameter(@Param("id") Integer id,
                                                        @Param("param_name") String param_name,
                                                        @Param("min_value") BigDecimal min_value,
                                                        @Param("max_value") BigDecimal max_value
    );

    /*
     * Delete Normalization Parameter Data
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Normalization_params WHERE id = :id", nativeQuery = true)
    void deleteNormalizationParameter(@Param("id") Integer id);
}
