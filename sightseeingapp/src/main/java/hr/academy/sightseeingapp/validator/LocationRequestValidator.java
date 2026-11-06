package hr.academy.sightseeingapp.validator;

import hr.academy.sightseeingapp.exception.IllegalRequestParametersException;
import hr.academy.sightseeingapp.model.Attraction;
import hr.academy.sightseeingapp.model.User;
import org.springframework.stereotype.Component;

@Component
public class LocationRequestValidator {

    public void validateNew (Attraction attraction, User user) {
        for (Attraction a : user.getFavourites()) {
            if (a.getId() == attraction.getId()) {
                throw new IllegalRequestParametersException("Atrakcija je već u favoritima");
            }
        }
        user.getFavourites().add(attraction);
    }
}
