package hr.academy.sightseeingapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Attractions", schema = "SIGHTSEEING_APP")
public class Attraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String attractionURLName;
    @ManyToOne
    @JoinColumn(name = "ID_LOCATION", nullable = false)
    private Location location;
    @ManyToOne
    @JoinColumn(name = "ID_TYPE", nullable = false)
    private Type type;
    @OneToMany(mappedBy = "attraction", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Picture> pictureList;

    @OneToMany(mappedBy = "attraction", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Review> reviews;


    public Attraction(String attractionURLName, String description, String name) {
        this.attractionURLName = attractionURLName;
        this.description = description;
        this.name = name;
    }

    public Attraction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getAttractionURLName() {
        return attractionURLName;
    }

    public void setAttractionURLName(String attractionURLName) {
        this.attractionURLName = attractionURLName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
