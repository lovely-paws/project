package edu.johnshopkins.lovelypaws.tags;

import edu.johnshopkins.lovelypaws.Role;
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

    private User actionsFor;
    public void setActionsFor(User user) { this.actionsFor = user; }

    private boolean detailed;
    public void setDetailed(boolean detailed) { this.detailed = detailed; }

    public void doTag() throws JspTagException, IOException {
        StringBuilder sb = new StringBuilder();
        if(listing == null) {
            getJspContext().getOut().print("<b>NO LISTING PROVIDED!</b>");
            return;
        }

        sb.append("<div class='listingInfo'>");
        sb.append("<h3>").append(listing.getName()).append("</h3>");
        sb.append("<table>")
                .append("<tr>")
                    .append("<td>Type</td>")
                    .append("<td>").append(listing.getAnimalType().getName()).append("</td>")
                .append("</tr><tr>")
                    .append("<td>Color</td>")
                    .append("<td>").append(listing.getColor()).append("</td>")
                .append("</tr><tr>")
                    .append("<td>Gender</td>")
                    .append("<td>").append(listing.getGender()).append("</td>")
                .append("</tr><tr>")
                    .append("<td>Age</td>")
                    .append("<td>").append(listing.getAge()).append("</td>")
                .append("</tr><tr>")
                    .append("<td>Description</td>")
                    .append("<td>").append(listing.getDescription()).append("</td>")
                .append("</tr><tr>")
                    .append("<td>Shelter</td>")
                    .append(String.format("<td><a href='%s/shelter/view/%d'>%s</td>",
                            baseUrl, listing.getShelter().getId(), listing.getShelter().getName()))
                .append("</tr>");

        if(actionsFor != null) {
            if(actionsFor.getRole() == Role.END_USER) {
                sb.append(String.format("<tr><td colspan='2'><a href='%s/cart/add/%d'>Add to Cart</a></td></tr>",
                        baseUrl, listing.getId()));
            } else if(actionsFor.getRole() == Role.ADMINISTRATOR || actionsFor.getId() == listing.getShelter().getId()) {
                sb.append(String.format("<tr><td colspan='2'><a href='%s/listing/edit/%d'>Edit Listing</a></td></tr>",
                        baseUrl, listing.getId()));
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
