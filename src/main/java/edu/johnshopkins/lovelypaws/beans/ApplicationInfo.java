package edu.johnshopkins.lovelypaws.beans;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class ApplicationInfo implements Serializable {

    private String why;
    public String getWhy() { return why; }
    public void setWhy(String why) { this.why = StringUtils.trimToNull(why); }

    private boolean accepted;
    public boolean isAccepted() { return accepted; }
    public void setAccepted(boolean accepted) { this.accepted = accepted; }
}
