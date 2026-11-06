package hr.academy.sightseeingapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TYPES", schema = "SIGHTSEEING_APP")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column private String name;

    public Type(String name) {
        this.name = name;
    }

    public Type() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
