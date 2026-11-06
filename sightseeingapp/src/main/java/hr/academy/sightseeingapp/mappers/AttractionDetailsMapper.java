package hr.academy.sightseeingapp.mappers;

import hr.academy.sightseeingapp.dto.AttractionDto;
import hr.academy.sightseeingapp.dto.AttractionOnLocationDto;
import hr.academy.sightseeingapp.model.Attraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ReviewMapper.class})
public interface AttractionDetailsMapper {
    AttractionDetailsMapper INSTANCE = Mappers.getMapper(AttractionDetailsMapper.class);


    @Mapping(target = "type", source = "type.name")
    @Mapping(target = "averageRating", ignore = true)
    AttractionDto toDto(Attraction attraction);

    @Mapping(target = "type", source = "type.name")
    @Mapping(target = "averageRating", ignore = true)
    List<AttractionDto> toDto(List<Attraction> attractionList);
}
