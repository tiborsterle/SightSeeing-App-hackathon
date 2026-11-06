package hr.academy.sightseeingapp.controller;

import hr.academy.sightseeingapp.dto.TravelJournalCreationResult;
import hr.academy.sightseeingapp.dto.TravelJournalDto;
import hr.academy.sightseeingapp.dto.UserCreationResult;
import hr.academy.sightseeingapp.service.TravelJournalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/travel-journal")
@Tag(name = "Putni dnevnik", description = "Upravljanje putnim dnevnicima usera")
public class TravelJournalController {

    private TravelJournalService travelJournalService;

    @Autowired
    public TravelJournalController(TravelJournalService travelJournalService) {
        this.travelJournalService = travelJournalService;
    }

    @PostMapping("/{userId}")
    @Operation(summary = "Kreiraj putopis")
    public ResponseEntity<TravelJournalDto> saveTravelJournal(@PathVariable Long userId, @RequestBody TravelJournalDto travelJournalDto) {
        TravelJournalCreationResult result = travelJournalService.saveTravelJournalByUserId(userId, travelJournalDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(result.id())
                .toUri();
        return ResponseEntity.created(location).body(result.travelJournalDto());
    }

    @PatchMapping("/{travelJournalId}")
    @Operation(summary = "Nadopuni putopis")
    public ResponseEntity<TravelJournalDto> updateTravelJournal(@PathVariable Long travelJournalId, @RequestBody TravelJournalDto travelJournalDto) {
        return ResponseEntity.ok(travelJournalService.updateTravelJournal(travelJournalId, travelJournalDto));
    }

    @GetMapping("/{travelJournalId}")
    @Operation(summary = "Dohvati putopis")
    public ResponseEntity<TravelJournalDto> fetchTravelJournal(@PathVariable Long travelJournalId) {
        return ResponseEntity.ok(travelJournalService.fetchTravelJournal(travelJournalId));
    }

}