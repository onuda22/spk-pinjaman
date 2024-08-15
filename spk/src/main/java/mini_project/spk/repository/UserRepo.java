package mini_project.spk.repository;

import jakarta.transaction.Transactional;
import mini_project.spk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM Users", nativeQuery = true)
    List<User> findUserAll();

    @Transactional
    @Query(
            value = "INSERT INTO Users (username, password) VALUES (:username, :password) RETURNING *",
            nativeQuery = true
    )
    User saveUser(@Param("username") String username,
                  @Param("password") String password);
}
