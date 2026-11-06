package hr.academy.sightseeingapp.dto;

import java.sql.Timestamp;

public record AttractionReviewDto (Timestamp timestamp, Double rating, String reviewText){
}
