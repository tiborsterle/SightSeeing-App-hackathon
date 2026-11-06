package hr.academy.sightseeingapp.dto;

import java.util.List;

public class AttractionDto {
    private String name;
    private String description;
    private String type;
    private Double averageRating;
    private List<AttractionDetailsReviewDto> reviews;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public List<AttractionDetailsReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<AttractionDetailsReviewDto> reviews) {
        this.reviews = reviews;
    }
}
