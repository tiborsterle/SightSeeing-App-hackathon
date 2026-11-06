package hr.academy.sightseeingapp.model;


import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "REVIEWS", schema = "SIGHTSEEING_APP")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column private Timestamp timestamp;
    @Column private Double rating;
    @Column private String reviewText;
    @ManyToOne
    @JoinColumn(name = "ID_ATTRACTION", nullable = false)
    private Attraction attraction;

    public Review(Timestamp timestamp, String reviewText, Double rating, Attraction attraction) {
        this.timestamp = timestamp;
        this.reviewText = reviewText;
        this.rating = rating;
        this.attraction = attraction;
    }

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
