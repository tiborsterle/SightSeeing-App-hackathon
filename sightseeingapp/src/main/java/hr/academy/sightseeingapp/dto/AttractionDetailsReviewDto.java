package hr.academy.sightseeingapp.dto;

import java.sql.Timestamp;

public record AttractionDetailsReviewDto(Timestamp timestamp, Double rating, String reviewText) {
}
