package hr.academy.sightseeingapp.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "TRAVEL_JOURNALS", schema = "SIGHTSEEING_APP")
public class TravelJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "ID_USER", nullable = false)
    private User user;
    @OneToMany(mappedBy = "travelJournal", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<JournalAttractionsDesc> journalAttractionsDesc;


    public TravelJournal(LocalDate startDate, LocalDate endDate, String description, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.user = user;
    }

    public TravelJournal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<JournalAttractionsDesc> getJournalAttractionsDesc() {
        return journalAttractionsDesc;
    }

    public void setJournalAttractionsDesc(List<JournalAttractionsDesc> journalAttractionsDesc) {
        this.journalAttractionsDesc = journalAttractionsDesc;
    }
}
