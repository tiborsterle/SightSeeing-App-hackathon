package hr.academy.sightseeingapp.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "LOCATIONS", schema = "SIGHTSEEING_APP")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Attraction> attractionlist;

    public Location(List<Attraction> attractionlist, String name) {
        this.attractionlist = attractionlist;
        this.name = name;
    }

    public Location() {
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

    public List<Attraction> getAttractionlist() {
        return attractionlist;
    }

    public void setAttractionlist(List<Attraction> attractionlist) {
        this.attractionlist = attractionlist;
    }
}
