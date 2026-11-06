package hr.academy.sightseeingapp.validator;

import hr.academy.sightseeingapp.dto.TravelJournalDto;
import hr.academy.sightseeingapp.exception.IllegalRequestParametersException;
import org.springframework.stereotype.Component;

@Component
public class TravelJournalValidator {

    public void validate(TravelJournalDto travelJournalDto) {
        if (travelJournalDto.getStartDate() == null) {
            throw new IllegalRequestParametersException("Start date je obavezan");
        }
        if (travelJournalDto.getDescription() == null) {
            throw new IllegalRequestParametersException("Description je obavezan");
        }
        if (travelJournalDto.getAttractions() == null) {
            throw new IllegalRequestParametersException("Attractions je obavezan");
        }
    }
}
