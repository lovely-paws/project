package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.AdoptionRequestResult;
import edu.johnshopkins.lovelypaws.beans.ApplicationInfo;
import edu.johnshopkins.lovelypaws.dao.AdoptionRequestDao;
import edu.johnshopkins.lovelypaws.dao.ApplicationDao;
import edu.johnshopkins.lovelypaws.dao.ListingDao;
import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.AdoptionRequest;
import edu.johnshopkins.lovelypaws.entity.Application;
import edu.johnshopkins.lovelypaws.entity.EndUser;
import edu.johnshopkins.lovelypaws.entity.Listing;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class AdoptionRequestBo {

    @Autowired
    private AdoptionRequestDao adoptionRequestDao;

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private ListingDao listingDao;

    @Transactional
    public void create(ApplicationInfo applicationInfo) {
        if(applicationInfo == null) {
            throw new IllegalArgumentException("applicationInfo must not be null");
        } else if(StringUtils.length(applicationInfo.getWhy()) < 10) {
            throw new IllegalArgumentException("The provided justification is too short.");
        } else if(!applicationInfo.isAccepted()) {
            throw new IllegalArgumentException("You must accept the agreement.");
        } else if(applicationInfo.getListingIds().size() == 0) {
            throw new IllegalArgumentException("No listings are associated with this request.");
        } else if(applicationInfo.getEndUser() == null) {
            throw new IllegalArgumentException("No user is associated with this request.");
        }

        Application application = new Application();
        application.setWhy(applicationInfo.getWhy());
        application = applicationDao.persist(application);

        for(Listing listing : listingDao.findByIds(applicationInfo.getListingIds())) {
            AdoptionRequest adoptionRequest = new AdoptionRequest();
            adoptionRequest.setAdoptionRequestResult(AdoptionRequestResult.PENDING);
            adoptionRequest.setApplication(application);
            adoptionRequest.setEndUser(applicationInfo.getEndUser());
            adoptionRequest.setListing(listing);
            adoptionRequestDao.persist(adoptionRequest);
        }
    }

    public void close(long id, boolean accepted) {
        AdoptionRequest adoptionRequest = adoptionRequestDao.findById(id);
        if(adoptionRequest.getListing().isVisible() && accepted) {
            adoptionRequest.getListing().setVisible(false);
            adoptionRequest.setListing(listingDao.merge(adoptionRequest.getListing()));

        }
        adoptionRequest.setAdoptionRequestResult(accepted ? AdoptionRequestResult.APPROVED : AdoptionRequestResult.REJECTED);
        adoptionRequestDao.merge(adoptionRequest);

        if(accepted) {
            for (AdoptionRequest otherAdoptionRequest : adoptionRequestDao.findAll()) {
                if (otherAdoptionRequest.getListing().getId() == adoptionRequest.getListing().getId() && adoptionRequest.getId() != otherAdoptionRequest.getId()) {
                    otherAdoptionRequest.setAdoptionRequestResult(AdoptionRequestResult.REJECTED);
                    adoptionRequestDao.merge(otherAdoptionRequest);
                }
            }
        }
    }
}
