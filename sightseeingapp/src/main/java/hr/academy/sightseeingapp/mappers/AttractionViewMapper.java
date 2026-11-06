package hr.academy.sightseeingapp.mappers;

import hr.academy.sightseeingapp.dto.AttractionOnLocationDto;
import hr.academy.sightseeingapp.dto.AttractionViewDto;
import hr.academy.sightseeingapp.model.Attraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AttractionViewMapper {
    AttractionViewMapper INSTANCE = Mappers.getMapper(AttractionViewMapper.class);


    @Mapping(target = "type", source = "type.name")
    @Mapping(target = "location", source = "location.name")
    AttractionViewDto toDto(Attraction attraction);

    @Mapping(target = "type", source = "type.name")
    @Mapping(target = "location", source = "location.name")
    List<AttractionViewDto> toDto(List<Attraction> attractionList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "attractionURLName", ignore = true)
    @Mapping(target = "pictureList", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    Attraction toAttraction(AttractionViewDto attractionViewDto);
}
