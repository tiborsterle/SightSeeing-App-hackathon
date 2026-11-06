package hr.academy.sightseeingapp.mappers;

import hr.academy.sightseeingapp.dto.AttractionOnLocationDto;
import hr.academy.sightseeingapp.model.Attraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {LocationMapper.class})
public interface AttractionOnLocationMapper {
    AttractionOnLocationMapper INSTANCE = Mappers.getMapper(AttractionOnLocationMapper.class);


    @Mapping(target = "type", source = "type.name")
    AttractionOnLocationDto toDto(Attraction attraction);

    @Mapping(target = "type", source = "type.name")
    List<AttractionOnLocationDto> toDto(List<Attraction> attractionList);

}
