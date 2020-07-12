package unj.example.unjchp6.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import unj.example.unjchp6.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);
}
