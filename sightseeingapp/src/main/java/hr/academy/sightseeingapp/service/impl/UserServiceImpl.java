package hr.academy.sightseeingapp.service.impl;

import hr.academy.sightseeingapp.dto.AttractionDto;
import hr.academy.sightseeingapp.dto.FavouriteAttractionDto;
import hr.academy.sightseeingapp.dto.UserCreationResult;
import hr.academy.sightseeingapp.dto.UserDto;
import hr.academy.sightseeingapp.exception.NoAttractionFoundException;
import hr.academy.sightseeingapp.exception.NoUserFoundException;
import hr.academy.sightseeingapp.mappers.FavouriteAttractionMapper;
import hr.academy.sightseeingapp.mappers.UserMapper;
import hr.academy.sightseeingapp.model.Attraction;
import hr.academy.sightseeingapp.model.User;
import hr.academy.sightseeingapp.repository.AttractionRepository;
import hr.academy.sightseeingapp.repository.LocationRepository;
import hr.academy.sightseeingapp.repository.UserRepository;
import hr.academy.sightseeingapp.service.UserService;
import hr.academy.sightseeingapp.validator.LocationRequestValidator;
import hr.academy.sightseeingapp.validator.UserRequestValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserRequestValidator userRequestValidator;

    private final AttractionRepository attractionRepository;
    private final FavouriteAttractionMapper favouriteAttractionMapper;
    private final LocationRequestValidator locationRequestValidator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserRequestValidator userRequestValidator, LocationRequestValidator locationRequestValidator, AttractionRepository attractionRepository, FavouriteAttractionMapper favouriteAttractionMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userRequestValidator = userRequestValidator;
        this.locationRequestValidator = locationRequestValidator;
        this.attractionRepository = attractionRepository;
        this.favouriteAttractionMapper = favouriteAttractionMapper;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoUserFoundException("User not found"));

        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserCreationResult saveUser(UserDto userDto) {
        userRequestValidator.validate(userDto);
        User user = userMapper.toUser(userDto);
        User savedUser = userRepository.save(user);
        return new UserCreationResult(savedUser.getId(), userMapper.toDto(savedUser));
    }

    @Override
    public List<FavouriteAttractionDto> getUserFavouriteAttractionsById(Long id) {
        User user =  userRepository.findById(id).orElseThrow(() -> new NoUserFoundException("User not found"));

        return favouriteAttractionMapper.toDto(user.getFavourites());
    }

    @Override
    public void saveUserFavouriteAttraction(FavouriteAttractionDto favouriteAttractionDto, Long userId) {
        Optional<Attraction> attraction = attractionRepository.findAttractionByNameAndLocation_Name(
                favouriteAttractionDto.attractionName(),
                favouriteAttractionDto.location()
        );
        if (attraction.isEmpty()) {
            throw new NoAttractionFoundException("Atrakcija nije pronađena!");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new NoUserFoundException("Korisnik nije pronađen!"));

        locationRequestValidator.validateNew(attraction.get(), user);

        userRepository.save(user);
    }
}
