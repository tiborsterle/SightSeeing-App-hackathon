package hr.academy.sightseeingapp.repository;

import hr.academy.sightseeingapp.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findTypeByName(String name);
}
