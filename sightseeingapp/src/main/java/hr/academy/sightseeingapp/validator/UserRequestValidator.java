package hr.academy.sightseeingapp.validator;

import hr.academy.sightseeingapp.dto.AttractionDto;
import hr.academy.sightseeingapp.dto.FavouriteAttractionDto;
import hr.academy.sightseeingapp.dto.UserDto;
import hr.academy.sightseeingapp.exception.IllegalRequestParametersException;
import hr.academy.sightseeingapp.exception.NoAttractionFoundException;
import hr.academy.sightseeingapp.model.Attraction;
import hr.academy.sightseeingapp.model.Location;
import hr.academy.sightseeingapp.model.User;
import hr.academy.sightseeingapp.repository.AttractionRepository;
import hr.academy.sightseeingapp.repository.LocationRepository;
import hr.academy.sightseeingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserRequestValidator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttractionRepository attractionRepository;

    @Autowired
    private LocationRepository locationRepository;

    public void validate(UserDto user){
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalRequestParametersException("Ime je obavezno");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalRequestParametersException("Email je obavezan");
        }
        if (!emailValidator(user.getEmail())) {
            throw new IllegalRequestParametersException("Email nije tocan format!");
        }
        for (User u : userRepository.findAll()) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new IllegalRequestParametersException("Već postoji korisnik s tim emailom");
            }
        }
        if (user.getAddress() != null && user.getAddress().country() != null && !countryValidator(user.getAddress().country())) {
                throw new IllegalRequestParametersException("Country code mora biti 2 velika slova engleske abecede");
        }
    }

    public boolean countryValidator(String country) {
        return country.matches("^[A-Z]+$") && country.length() == 2;
    }

    public boolean emailValidator(String email) {
        return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }
}
