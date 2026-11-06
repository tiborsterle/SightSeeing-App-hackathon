package hr.academy.sightseeingapp.dto;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class ReviewDto {
    @NotNull(message = "Lokacija je obavezna.")
    String location;

    @NotNull(message = "Ime atrakcije je obavezno.")
    String attractionName;

    @NotNull(message = "Timestamp je obavezan.")
    Timestamp timestamp;

    @NotNull(message = "Ocjena je obavezna")
    @DecimalMin(value = "1.0", message = "Ocjena mora biti najmanje 1")
    @DecimalMax(value = "5.0", message = "Ocjena mora biti najviše 5")
    Double rating;

    @NotNull(message = "Opis recenzije je obavezan.")
    String reviewText;

    public ReviewDto(){

    }

    public ReviewDto(String location, String attractionName, Timestamp timestamp, Double rating, String reviewText) {
        this.location = location;
        this.attractionName = attractionName;
        this.timestamp = timestamp;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
