package hr.academy.sightseeingapp.service;

import hr.academy.sightseeingapp.dto.TravelJournalCreationResult;
import hr.academy.sightseeingapp.dto.TravelJournalDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface TravelJournalService {
    TravelJournalCreationResult saveTravelJournalByUserId(Long userId, TravelJournalDto travelJournalDto);
    TravelJournalDto updateTravelJournal(Long travelJournalId, TravelJournalDto travelJournalDto);
    TravelJournalDto fetchTravelJournal(Long travelJournalId);
}
