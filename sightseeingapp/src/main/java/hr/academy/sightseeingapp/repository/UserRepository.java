package hr.academy.sightseeingapp.repository;

import hr.academy.sightseeingapp.dto.UserDto;
import hr.academy.sightseeingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    UserDto[] getAllByEmail(String email);
}
