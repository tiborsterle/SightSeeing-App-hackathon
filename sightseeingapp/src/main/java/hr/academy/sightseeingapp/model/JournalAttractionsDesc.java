package hr.academy.sightseeingapp.model;

import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
@Table(name = "JOURNAL_ATTRACTIONS_DESC", schema = "SIGHTSEEING_APP")
public class JournalAttractionsDesc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column private String comment;
    @Column private LocalDate localDate;
    @ManyToOne
    @JoinColumn(name = "ID_TRAVEL_JOURNAL", nullable = false)
    private TravelJournal travelJournal;
    @ManyToOne
    @JoinColumn(name = "ID_ATTRACTION", nullable = false)
    private Attraction attraction;


    public JournalAttractionsDesc(String comment, LocalDate localDate) {
        this.comment = comment;
        this.localDate = localDate;
    }

    public JournalAttractionsDesc() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TravelJournal getTravelJournal() {
        return travelJournal;
    }

    public void setTravelJournal(TravelJournal travelJournal) {
        this.travelJournal = travelJournal;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }
}
