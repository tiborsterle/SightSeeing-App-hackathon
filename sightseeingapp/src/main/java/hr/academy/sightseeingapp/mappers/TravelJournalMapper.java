package hr.academy.sightseeingapp.mappers;

import hr.academy.sightseeingapp.dto.TravelJournalDto;
import hr.academy.sightseeingapp.model.TravelJournal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = JournalAttractionsDescMapper.class)
public interface TravelJournalMapper {
    TravelJournalMapper INSTANCE = Mappers.getMapper(TravelJournalMapper.class);

    @Mapping(target = "attractions", source = "journalAttractionsDesc")
    TravelJournalDto toDto(TravelJournal travelJournal);
    List<TravelJournalDto> toDto(List<TravelJournal> travelJournalList);
}
