package hr.academy.sightseeingapp.controller;

import hr.academy.sightseeingapp.dto.AttractionDto;
import hr.academy.sightseeingapp.dto.LocationDto;
import hr.academy.sightseeingapp.dto.ReviewDto;
import hr.academy.sightseeingapp.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;


@RestController
@RequestMapping("/attractions")
@Tag(name = "Atrakcije", description = "Upravljanje atrakcijama")
public class AttractionController {
    private final AttractionService attractionService;

    public AttractionController(AttractionService attractionService){
        this.attractionService = attractionService;
    }

    @GetMapping("/{location}")
    @Operation(summary = "Dohvati popis atrakcija za određenu lokaciju")
    public ResponseEntity<LocationDto> getAttractionsOnLocation(@PathVariable("location") String location){
        LocationDto response = attractionService.getAttractionsOnLocation(location);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/review")
    @Operation(summary = "Ocijeni atrakciju")
    public ResponseEntity<Void> postReview(@Valid @RequestBody ReviewDto review){
        attractionService.saveReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{location}/{attractionURLName}")
    @Operation(summary = "Dohvati detalje atrakcije")
    public ResponseEntity<AttractionDto> getAttractionDetails(
            @PathVariable("location") String location,
            @PathVariable("attractionURLName") String attractionURLName,
            @RequestParam(value = "excludeReviews", required = false, defaultValue = "false") boolean excludeReviews,
            @RequestParam(value = "reviewsFrom", required = false) LocalDate reviewsFrom,
            @RequestParam(value = "reviewsTo", required = false) LocalDate reviewsTo){
        AttractionDto attractionDto = attractionService.getAttractionDetails(location, attractionURLName, excludeReviews, reviewsFrom, reviewsTo);
        return ResponseEntity.ok().body(attractionDto);
    }

    @PostMapping(value = "/{location}/{attractionURLname}/picture",
    consumes = MediaType.IMAGE_PNG_VALUE)
    @Operation(summary = "Dodaj sliku za atrakciju")
    public ResponseEntity<Void> postPictureToAttraction(@PathVariable("location") String location, @PathVariable("attractionURLname") String attractionURLName, @RequestBody byte[] imageBytes){
        Long id = attractionService.savePicture(location, attractionURLName, imageBytes);
        URI pictureLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(pictureLocation).build();
    }


    @GetMapping(value = "/{location}/{attractionURLname}/picture/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    @Operation(summary = "Dohvati sliku atrakcije")
    public ResponseEntity<byte[]> getPictureOfAttraction(@PathVariable("location") String location, @PathVariable("attractionURLname") String attractionURLName, @PathVariable("id") Long id){
        byte[] imageBytes = attractionService.getPicture(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }
}
