package edu.johnshopkins.lovelypaws.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Application {

    @Id
    @GeneratedValue
    private long id;
    public long getId() { return id; }

    @Column
    private String why;
    public String getWhy() { return why; }
    public void setWhy(String why) { this.why = why; }

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private List<AdoptionRequest> adoptionRequests = new ArrayList<>();
    public List<AdoptionRequest> getAdoptionRequests() { return adoptionRequests; }
    public void setAdoptionRequests(List<AdoptionRequest> adoptionRequests) { this.adoptionRequests = adoptionRequests; }
}
