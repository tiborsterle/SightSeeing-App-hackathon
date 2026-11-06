package hr.academy.sightseeingapp.mappers;

import hr.academy.sightseeingapp.dto.AddressDto;
import hr.academy.sightseeingapp.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto toDto(Address address);
    List<AddressDto> toDto(List<Address> addressList);
    @Mapping(target = "id", ignore = true)
    Address toAddress(AddressDto addressDto);

}
