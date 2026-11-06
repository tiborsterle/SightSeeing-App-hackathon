package hr.academy.sightseeingapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AttractionViewDto{
    @NotEmpty(message="Lokacija ne smije biti prazna.")
            @Size(max=30, message = "Lokacija ne smije imati više od 30 znakova.")
    String location;

    @NotEmpty(message="Naziv ne smije biti prazan.")
    @Size(max=50, message = "Naziv ne smije imati više od 50 znakova.")
    String name;

    @NotEmpty(message="Opis ne smije biti prazan.")
    @Size(max=250, message = "Opis ne smije imati više od 250 znakova.")
    String description;

    @NotEmpty(message="Tip ne smije biti prazan.")
    String type;

    public AttractionViewDto(String location, String name, String description, String type) {
        this.location = location;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public AttractionViewDto() {

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
