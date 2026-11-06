package hr.academy.sightseeingapp.repository;

import hr.academy.sightseeingapp.model.TravelJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelJournalRepository extends JpaRepository<TravelJournal, Long> {
}
