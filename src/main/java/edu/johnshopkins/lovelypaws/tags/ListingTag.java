package edu.johnshopkins.lovelypaws.tags;

import edu.johnshopkins.lovelypaws.Role;
import edu.johnshopkins.lovelypaws.entity.*;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import static java.lang.String.format;

public class ListingTag extends SimpleTagSupport {
    private Listing listing;
    public void setListing(Listing listing){ this.listing = listing; }

    private String baseUrl;
    public void setBaseUrl(String baseUrl){ this.baseUrl = baseUrl; }

    private User viewer;
    public void setViewer(User viewer) { this.viewer = viewer; }

    private boolean detailed;
    public void setDetailed(boolean detailed) { this.detailed = detailed; }

    public void doTag() throws JspTagException, IOException {
        StringBuilder sb = new StringBuilder();
        if(listing == null) {
            getJspContext().getOut().print("<div class='warning'>Null listing provided.</div>");
            return;
        }

        sb.append("<div class='listing'>");
            sb.append(format("<img src='%s/listing/image/%d' class='listing-image' />", baseUrl, listing.getId()));

        sb.append("<table class='listing-table'>")
                .append(format("<tr><td colspan='2'>[Listing #%d] %s</td></tr>", listing.getId(), listing.getName()))
                .append(format("<tr><td>Type</td><td>%s</td></tr>", listing.getAnimalType().getName()))
                .append(format("<tr><td>Color</td><td>%s</td></tr>", listing.getColor()))
                .append(format("<tr><td>Gender</td><td>%s</td></tr>", listing.getGender()))
                .append(format("<tr><td>Age</td><td>%s</td></tr>", listing.getAge()))
                .append(format("<tr><td>Description</td><td>%s</td></tr>", listing.getDescription()))
                .append(format("<tr><td>Shelter</td><td><a href='%s/shelter/view/%d'>%s</td></tr>",
                            baseUrl, listing.getShelter().getId(), listing.getShelter().getName()));

        if(viewer != null && viewer.getRole() != null) {
            switch(viewer.getRole()) {
                case ADMINISTRATOR:
                    sb.append(String.format("<tr><td colspan='2'><a href='%s/listing/edit/%d'>Edit Listing</a></td></tr>",
                            baseUrl, listing.getId()));
                    sb.append(String.format("<tr><td colspan='2'><a href='%s/listing/delete/%d'>Delete Listing</a></td></tr>",
                            baseUrl, listing.getId()));
                    break;
                case SHELTER:
                    if(viewer.getId() == listing.getShelter().getId()) {
                        sb.append(String.format("<tr><td colspan='2'><a href='%s/listing/edit/%d'>Edit Listing</a></td></tr>",
                                baseUrl, listing.getId()));
                        sb.append(String.format("<tr><td colspan='2'><a href='%s/listing/delete/%d'>Delete Listing</a></td></tr>",
                                baseUrl, listing.getId()));
                    }
                    break;
                case END_USER: // Fall-through.
                default:
                    sb.append(String.format("<tr><td colspan='2'><a href='%s/cart/add/%d'>Add to Cart</a></td></tr>",
                            baseUrl, listing.getId()));
                    break;
            }
        }

        if(!detailed) {
            sb.append(String.format("<tr><td colspan='2'><a href='%s/listing/view/%d'>View Listing</a></td></tr>",
                    baseUrl, listing.getId()));
        }

        sb.append("</table>");
        sb.append("</div>");
        getJspContext().getOut().print(sb.toString());
    }
}
