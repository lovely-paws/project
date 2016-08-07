package edu.johnshopkins.lovelypaws.entity;

import edu.johnshopkins.lovelypaws.AdoptionRequestResult;

import javax.persistence.*;

@Entity
public class AdoptionRequest {

    @Id
    @GeneratedValue
    private long id;
    public long getId() { return id; }

    @ManyToOne
    private EndUser endUser;
    public EndUser getEndUser() { return endUser; }
    public void setEndUser(EndUser endUser) { this.endUser = endUser; }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Application.class)
    private Application application;
    public Application getApplication() { return application; }
    public void setApplication(Application application) { this.application = application; }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Listing.class)
    private Listing listing;
    public Listing getListing() { return listing; }
    public void setListing(Listing listing) { this.listing = listing; }

    @Column
    private AdoptionRequestResult adoptionRequestResult;
    public AdoptionRequestResult getAdoptionRequestResult() { return adoptionRequestResult; }
    public void setAdoptionRequestResult(AdoptionRequestResult adoptionRequestResult) { this.adoptionRequestResult = adoptionRequestResult; }

}
