package hr.academy.sightseeingapp.service.impl;

import hr.academy.sightseeingapp.dto.*;
import hr.academy.sightseeingapp.exception.NoAttractionFoundException;
import hr.academy.sightseeingapp.exception.NoImageFoundException;
import hr.academy.sightseeingapp.exception.NoTypeFoundException;
import hr.academy.sightseeingapp.mappers.AttractionDetailsMapper;
import hr.academy.sightseeingapp.mappers.AttractionViewMapper;
import hr.academy.sightseeingapp.mappers.LocationMapper;
import hr.academy.sightseeingapp.model.*;
import hr.academy.sightseeingapp.repository.*;
import hr.academy.sightseeingapp.service.AttractionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttractionServiceImpl implements AttractionService {
    private final AttractionRepository attractionRepository;
    private final LocationRepository locationRepository;
    private final ReviewRepository reviewRepository;
    private final PictureRepository pictureRepository;
    private final TypeRepository typeRepository;
    private final LocationMapper locationMapper;
    private final AttractionDetailsMapper attractionDetailsMapper;
    private final AttractionViewMapper attractionViewMapper;

    @Autowired
    public AttractionServiceImpl(AttractionRepository attractionRepository,
                                 ReviewRepository reviewRepository,
                                 LocationRepository locationRepository,
                                 TypeRepository typeRepository,
                                 LocationMapper locationMapper,
                                 AttractionDetailsMapper attractionDetailsMapper,
                                 PictureRepository pictureRepository,
                                 AttractionViewMapper attractionViewMapper){
        this.attractionRepository = attractionRepository;
        this.locationRepository = locationRepository;
        this.reviewRepository = reviewRepository;
        this.pictureRepository = pictureRepository;
        this.locationMapper = locationMapper;
        this.attractionDetailsMapper = attractionDetailsMapper;
        this.attractionViewMapper = attractionViewMapper;
        this.typeRepository = typeRepository;
    }

    @Override
    public LocationDto getAttractionsOnLocation(String location){
        Optional<Location> locationEntity = locationRepository.findLocationByName(location);

        if(locationEntity.isEmpty()){
            return new LocationDto(location, List.of());
        }

        return locationMapper.toDto(locationEntity.get());
    }

    @Override
    public List<AttractionViewDto> getAttractions(){
        List<AttractionViewDto> attractions = attractionViewMapper.toDto(attractionRepository.findAll());
        return attractions;
    }

    @Override
    public List<Type> getTypes(){
        return typeRepository.findAll();
    }

    @Transactional
    @Override
    public void saveAttraction(AttractionViewDto attractionViewDto){
        Location location = locationRepository.findLocationByName(attractionViewDto.getLocation()).orElseGet(() -> {
           Location newLocation = new Location(null, attractionViewDto.getLocation());
           locationRepository.save(newLocation);
           return newLocation;
        });
        if(location == null){
            locationRepository.save(new Location(null, attractionViewDto.getLocation()));
        }
        Attraction attraction = attractionViewMapper.toAttraction(attractionViewDto);
        attraction.setLocation(location);
        attraction.setAttractionURLName(generateAttractionURLName(attraction.getName()));
        attraction.setType(typeRepository.findTypeByName(attractionViewDto.getType()).orElseThrow(() -> new NoTypeFoundException("Tip atrakcije ne postoji")));

        attractionRepository.save(attraction);
    }

    @Transactional
    @Override
    public void saveReview(ReviewDto reviewDto){
        Attraction attraction = attractionRepository.findAttractionByNameAndLocation_Name(reviewDto.getAttractionName(), reviewDto.getLocation()).orElseThrow(() -> new NoAttractionFoundException("Atrakcija ne postoji."));

        Review review = new Review(
                reviewDto.getTimestamp(),
                reviewDto.getReviewText(),
                reviewDto.getRating(),
                attraction
        );

        reviewRepository.save(review);
    }

    @Override
    public AttractionDto getAttractionDetails(String location, String attractionURLName, boolean excludeReviews, LocalDate reviewsFrom, LocalDate reviewsTo){
        AttractionDto attractionDto = attractionDetailsMapper
                .toDto(attractionRepository
                        .findAttractionByLocation_NameAndAttractionURLName(location, attractionURLName)
                        .orElseThrow(() -> new NoAttractionFoundException("Atrakcija ne postoji.")));


        List<AttractionDetailsReviewDto> includedReviews = attractionDto.getReviews();
        Double averageRating = calculateAverageRating(includedReviews);

        if(excludeReviews){
            includedReviews = List.of();
            attractionDto.setAverageRating(averageRating);
        }else{
            includedReviews = filterReviews(attractionDto.getReviews(), reviewsFrom, reviewsTo);
            if(includedReviews.isEmpty()){
                attractionDto.setAverageRating(averageRating);
            }
            attractionDto.setAverageRating(calculateAverageRating(includedReviews));
        }

        attractionDto.setReviews(includedReviews);
        return attractionDto;
    }

    private List<AttractionDetailsReviewDto> filterReviews(List<AttractionDetailsReviewDto> reviews, LocalDate reviewsFrom, LocalDate reviewsTo){
        List<AttractionDetailsReviewDto> filtered = reviews;
        if (reviewsFrom != null) {
            filtered = reviews.stream()
                    .filter(review -> {
                        LocalDate reviewDate = review.timestamp().toLocalDateTime().toLocalDate();
                        return reviewDate.isAfter(reviewsFrom);
                    })
                    .toList();

        }
        if(reviewsTo != null){
            filtered = reviews.stream()
                    .filter(review -> {
                        LocalDate reviewDate = review.timestamp().toLocalDateTime().toLocalDate();
                        return reviewDate.isBefore(reviewsTo);
                    })
                    .toList();
        }
        return filtered;
    }

    private Double calculateAverageRating(List<AttractionDetailsReviewDto> reviews){
        Double ratingSum = 0.0;
        for(var review : reviews){
            ratingSum += review.rating();
        }
        return ratingSum/reviews.size();
    }

    @Transactional
    @Override
    public Long savePicture(String location, String attractionURLName, byte[] imageBytes){
        if (imageBytes == null || imageBytes.length == 0){
            throw new NoImageFoundException("Morate dodati sliku!");
        }
        Attraction attraction = attractionRepository.findAttractionByLocation_NameAndAttractionURLName(location, attractionURLName)
                .orElseThrow(() -> new NoAttractionFoundException("Atrakcija ne postoji."));

        Picture savedPicture = pictureRepository.save(new Picture(imageBytes, attraction));

        return savedPicture.getId();
    }

    @Override
    public byte[] getPicture(Long id){
        Picture picture = pictureRepository.findById(id)
                .orElseThrow(() -> new NoImageFoundException("Slika nije pronađena."));
        return picture.getPictureData();
    }

    private String generateAttractionURLName(String name) {
        return name
                .replace("č", "c")
                .replace("ć", "c")
                .replace("š", "s")
                .replace("ž", "z")
                .replace("đ", "d")
                .replace("Č", "C")
                .replace("Ć", "C")
                .replace("Š", "S")
                .replace("Ž", "Z")
                .replace("Đ", "D")
                .replace(" ", "%20")
                .trim();
    }
}
