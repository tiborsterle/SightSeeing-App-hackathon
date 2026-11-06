package hr.academy.sightseeingapp.repository;

import hr.academy.sightseeingapp.model.Attraction;
import hr.academy.sightseeingapp.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findPicturesByAttraction(Attraction attraction);
}
