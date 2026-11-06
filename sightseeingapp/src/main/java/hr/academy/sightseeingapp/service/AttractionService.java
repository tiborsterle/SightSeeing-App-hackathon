package hr.academy.sightseeingapp.service;

import hr.academy.sightseeingapp.dto.AttractionDto;
import hr.academy.sightseeingapp.dto.AttractionViewDto;
import hr.academy.sightseeingapp.dto.LocationDto;
import hr.academy.sightseeingapp.dto.ReviewDto;
import hr.academy.sightseeingapp.model.Picture;
import hr.academy.sightseeingapp.model.Type;
import hr.academy.sightseeingapp.repository.AttractionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface AttractionService {

    LocationDto getAttractionsOnLocation(String location);

    List<AttractionViewDto> getAttractions();

    List<Type> getTypes();

    @Transactional
    void saveAttraction(AttractionViewDto attractionViewDto);

    void saveReview(ReviewDto reviewDto);

    AttractionDto getAttractionDetails(String location, String attractionURLName, boolean excludeReviews, LocalDate reviewsFrom, LocalDate reviewsTo);

    Long savePicture(String location, String attractionURLName, byte[] imageBytes);

    byte[] getPicture(Long id);
}