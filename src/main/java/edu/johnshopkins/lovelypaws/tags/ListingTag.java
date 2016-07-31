package edu.johnshopkins.lovelypaws.tags;

import edu.johnshopkins.lovelypaws.entity.*;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ListingTag extends SimpleTagSupport {
    private Listing listing;
    public void setListing(Listing listing){ this.listing = listing; }

    private String baseUrl;
    public void setBaseUrl(String baseUrl){ this.baseUrl = baseUrl; }

    public void doTag() throws JspTagException, IOException {
        StringBuilder sb = new StringBuilder();
        if(listing == null) {
            getJspContext().getOut().print("<b>NO LISTING PROVIDED!</b>");
            return;
        }

        sb.append("<div class='listingInfo'>")
                .append("<h3 class='listingInfo-label'><a href='")
                .append(baseUrl).append("/listing/view/").append(listing.getId()).append("'>[")
                .append(listing.getId()).append("] ").append(listing.getName()).append("</a></h3>");
        sb.append("<h4 class='listingInfo-label'>Type</h4><p class='listingInfo-value'>").append(listing.getAnimalType()).append("</p>");
        sb.append("<h4 class='listingInfo-label'>Color</h4><p class='listingInfo-value'>").append(listing.getColor()).append("</p>");
        sb.append("<h4 class='listingInfo-label'>Description</h4><p class='listingInfo-value'>").append(listing.getDescription()).append("</p>");
        sb.append("<h4 class='listingInfo-label'>Shelter</h4><p class='listingInfo-value'>").append(listing.getShelter().getName()).append("</p>");
        sb.append("</div>");
        getJspContext().getOut().print(sb.toString());
    }
}
