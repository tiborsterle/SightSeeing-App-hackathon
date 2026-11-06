package hr.academy.sightseeingapp.mappers;

import hr.academy.sightseeingapp.dto.AttractionDetailsReviewDto;
import hr.academy.sightseeingapp.dto.ReviewDto;
import hr.academy.sightseeingapp.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);


    AttractionDetailsReviewDto toDto(Review review);
    List<AttractionDetailsReviewDto> toDto(List<Review> reviewList);

}
