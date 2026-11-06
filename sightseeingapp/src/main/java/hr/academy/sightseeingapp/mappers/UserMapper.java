package hr.academy.sightseeingapp.mappers;

import hr.academy.sightseeingapp.dto.UserDto;
import hr.academy.sightseeingapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {AddressMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
    List<UserDto> toDto(List<User> userList);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "favourites", ignore = true)
    User toUser(UserDto userDto);
}
