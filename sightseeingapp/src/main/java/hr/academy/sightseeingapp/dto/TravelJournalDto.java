package hr.academy.sightseeingapp.dto;


import java.time.LocalDate;
import java.util.List;

public class TravelJournalDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private List<JournalAttractionsDescDto> attractions;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<JournalAttractionsDescDto> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<JournalAttractionsDescDto> attractions) {
        this.attractions = attractions;
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
}
