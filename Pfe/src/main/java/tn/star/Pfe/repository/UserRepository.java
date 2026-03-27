package tn.star.Pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.star.Pfe.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<Object> findById(int id);

    Optional<Object> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByActif(boolean actif);
}
