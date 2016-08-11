package edu.johnshopkins.lovelypaws.beans;

import edu.johnshopkins.lovelypaws.entity.EndUser;
import org.apache.commons.lang3.StringUtils;
import org.hsqldb.rights.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApplicationInfo implements Serializable {

    private EndUser endUser;
    public EndUser getEndUser() { return endUser; }
    public void setEndUser(EndUser endUser) { this.endUser = endUser; }

    private Set<Long> listingIds = new HashSet<>();
    public Set<Long> getListingIds() { return listingIds; }
    public void setListingIds(Set<Long> listingIds) { this.listingIds = listingIds; }

    private String why;
    public String getWhy() { return why; }
    public void setWhy(String why) { this.why = StringUtils.trimToNull(why); }

    private boolean accepted;
    public boolean isAccepted() { return accepted; }
    public void setAccepted(boolean accepted) { this.accepted = accepted; }
}
