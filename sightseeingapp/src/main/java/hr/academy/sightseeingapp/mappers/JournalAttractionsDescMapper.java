package hr.academy.sightseeingapp.mappers;

import hr.academy.sightseeingapp.dto.JournalAttractionsDescDto;
import hr.academy.sightseeingapp.model.JournalAttractionsDesc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {LocationMapper.class})
public interface JournalAttractionsDescMapper {

    JournalAttractionsDescMapper INSTANCE = Mappers.getMapper(JournalAttractionsDescMapper.class);

    @Mapping(target = "location", source = "attraction.location.name")
    @Mapping(target = "attraction", source = "attraction.name")
    @Mapping(target = "date", source = "localDate")
    JournalAttractionsDescDto toDto(JournalAttractionsDesc journalAttractionsDesc);

    List<JournalAttractionsDescDto> toDto(List<JournalAttractionsDesc> journalAttractionsDescs);
}

