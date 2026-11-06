package hr.academy.sightseeingapp.mappers;

import hr.academy.sightseeingapp.dto.FavouriteAttractionDto;
import hr.academy.sightseeingapp.model.Attraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FavouriteAttractionMapper {
    FavouriteAttractionDto INSTANCE = Mappers.getMapper(FavouriteAttractionDto.class);

    @Mapping(target = "location", source = "location.name")
    @Mapping(target = "attractionName", source = "name")
    FavouriteAttractionDto toDto(Attraction attraction);

    List<FavouriteAttractionDto> toDto(List<Attraction> attractions);


}
