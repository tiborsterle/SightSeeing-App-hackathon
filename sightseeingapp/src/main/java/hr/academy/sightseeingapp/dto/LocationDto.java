package hr.academy.sightseeingapp.dto;



import java.util.List;

public class LocationDto {
    private String name;
    private List<AttractionOnLocationDto> attractions;

    public LocationDto(String name, List<AttractionOnLocationDto> attractions) {
        this.name = name;
        this.attractions = attractions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AttractionOnLocationDto> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<AttractionOnLocationDto> attractions) {
        this.attractions = attractions;
    }
}
