package hr.academy.sightseeingapp.mappers;

import hr.academy.sightseeingapp.dto.LocationDto;
import hr.academy.sightseeingapp.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {AttractionOnLocationMapper.class})
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mapping(target = "attractions", source = "attractionlist")
    LocationDto toDto(Location location);

    @Mapping(target = "attractions", source = "attractionlist")
    List<LocationDto> toDto(List<Location> locationList);


}
