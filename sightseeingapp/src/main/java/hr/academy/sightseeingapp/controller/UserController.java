package hr.academy.sightseeingapp.controller;

import hr.academy.sightseeingapp.dto.FavouriteAttractionDto;
import hr.academy.sightseeingapp.dto.UserCreationResult;
import hr.academy.sightseeingapp.dto.UserDto;
import hr.academy.sightseeingapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "Korisnici", description = "Upravljanje korisnicima")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Dohvaćanje korisnika po ID")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/{id}/favorites")
    @Operation(summary = "Dohvaćanje korisnikovih najdražih atrakcija po ID")
    public ResponseEntity<List<FavouriteAttractionDto>> getUserFavouriteAttractions(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserFavouriteAttractionsById(id));
    }

    @PostMapping
    @Operation(summary = "Dodaj novog korisnika")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user){
        UserCreationResult result = userService.saveUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();
        return ResponseEntity.created(location).body(result.userDto());
    }

    @PostMapping("/{id}/favourites")
    @Operation(summary = "Dodaj novi userov favourite location")
    public ResponseEntity<FavouriteAttractionDto> addUserFavouriteAttraction(@PathVariable Long id, @RequestBody FavouriteAttractionDto favouriteAttractionDto){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        userService.saveUserFavouriteAttraction(favouriteAttractionDto, id);

        return ResponseEntity.status(HttpStatus.CREATED).header("Content-Location", location.toString()).build();
    }
}
