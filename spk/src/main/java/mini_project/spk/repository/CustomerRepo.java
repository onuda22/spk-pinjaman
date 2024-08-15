package mini_project.spk.repository;

import jakarta.transaction.Transactional;
import mini_project.spk.model.Customer;
import mini_project.spk.model.enums.CharacterRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    /*
    * Get All Customer Data
     */
    @Query(value = "SELECT * FROM Customers", nativeQuery = true)
    List<Customer> findCustomerAll();

    /*
    * Get Customer Data by Id
     */
    @Query(value = "SELECT * FROM Customers WHERE id = :id", nativeQuery = true)
    Customer findCustomerById(@Param("id") Integer id);

    /*
    * Create Customer Data
     */
    @Transactional
    @Query(value = "INSERT INTO Customers(name, age, income, credit_score, character_rating)" +
            "VALUES (:name, :age, :income, :credit_score, :character_rating) RETURNING *", nativeQuery = true)
    Customer saveCustomer(@Param("name") String name,
                          @Param("age") Integer age,
                          @Param("income") BigDecimal income,
                          @Param("credit_score") Integer credit_score,
                          @Param("character_rating") String character_rating
                          );

    /*
    * Update Customer Data
     */
    @Transactional
    @Query(value = "UPDATE Customers SET name = :name, age = :age, income = :income, credit_score = :credit_score, character_rating = :character_rating " +
            "WHERE id = :id RETURNING *", nativeQuery = true)
    Customer updateCustomer(@Param("id") Integer id,
                            @Param("name") String name,
                            @Param("age") Integer age,
                            @Param("income") BigDecimal income,
                            @Param("credit_score") Integer credit_score,
                            @Param("character_rating") String character_rating
                            );

    /*
    * Delete Customer Data
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Customers WHERE id = :id", nativeQuery = true)
    void deleteCustomer(@Param("id") Integer id);

}
