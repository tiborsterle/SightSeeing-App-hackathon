package hr.academy.sightseeingapp.repository;

import hr.academy.sightseeingapp.model.HTTPError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HttpErrorRepository extends JpaRepository<HTTPError, UUID> {
}
