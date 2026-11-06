package hr.academy.sightseeingapp.model;


import jakarta.persistence.*;

@Entity
@Table(name = "PICTURES", schema = "SIGHTSEEING_APP")
public class Picture{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column
    private byte[] pictureData;

    @ManyToOne
    @JoinColumn(name = "ID_ATTRACTION")
    private Attraction attraction;

    public Picture(byte[] pictureData, Attraction attraction) {
        this.pictureData = pictureData;
        this.attraction = attraction;
    }

    public Picture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPictureData() {
        return pictureData;
    }

    public void setPictureData(byte[] pictureData) {
        this.pictureData = pictureData;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }
}
