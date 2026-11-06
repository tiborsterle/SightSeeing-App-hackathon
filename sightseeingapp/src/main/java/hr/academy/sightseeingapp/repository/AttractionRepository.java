package hr.academy.sightseeingapp.repository;

import hr.academy.sightseeingapp.model.Attraction;
import hr.academy.sightseeingapp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    Optional<Attraction> findAttractionByNameAndLocation_Name(String name, String location);
    Optional<Attraction> findAttractionByLocation_NameAndAttractionURLName(String name, String location);
}
