package hr.academy.sightseeingapp.service;

import hr.academy.sightseeingapp.dto.AttractionDto;
import hr.academy.sightseeingapp.dto.FavouriteAttractionDto;
import hr.academy.sightseeingapp.dto.UserCreationResult;
import hr.academy.sightseeingapp.dto.UserDto;
import hr.academy.sightseeingapp.model.User;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);
    UserCreationResult saveUser(UserDto userDto);
    List<FavouriteAttractionDto> getUserFavouriteAttractionsById(Long id);
    void saveUserFavouriteAttraction(FavouriteAttractionDto favouriteAttractionDto, Long userId);
}
