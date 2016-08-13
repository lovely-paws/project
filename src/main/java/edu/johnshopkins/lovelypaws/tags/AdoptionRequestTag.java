package edu.johnshopkins.lovelypaws.tags;

import edu.johnshopkins.lovelypaws.Role;
import edu.johnshopkins.lovelypaws.entity.*;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import static java.lang.String.format;

public class AdoptionRequestTag extends SimpleTagSupport {
    private AdoptionRequest adoptionRequest;
    public void setAdoptionRequest(AdoptionRequest adoptionRequest){ this.adoptionRequest = adoptionRequest; }

    private String baseUrl;
    public void setBaseUrl(String baseUrl){ this.baseUrl = baseUrl; }

    public void doTag() throws JspTagException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='adoption-request cleared'>");
        if(adoptionRequest.getListing().getImageFile() != null) {
            sb.append(format("<img src='%s/listing/image/%d' class='listing-image' />", baseUrl, adoptionRequest.getListing().getId()));
        }

        sb.append("<table class='adoption-request-table'>")
                .append(format("<tr><td colspan='2'>Adoption Request #%d</td></tr>", adoptionRequest.getId()))
                .append(format("<tr><td>Submitter</td><td>%s</td></tr>", adoptionRequest.getEndUser().getName()))
                .append(format("<tr><td>Submitter Contact</td><td>%s</td></tr>", adoptionRequest.getEndUser().getEmailAddress()))
                .append(format("<tr><td>dSubmitter Comments</td><td>%s</td></tr>", adoptionRequest.getApplication().getWhy()))
                .append(format("<tr><td colspan='2'><a href='%s/listing/view/%d'>View Listing</a></td></tr>", baseUrl, adoptionRequest.getListing().getId()))
                .append(format("<tr><td colspan='2'><a href='%s/shelter/close/%d?result=false'>Decline Request</td></tr>", baseUrl, adoptionRequest.getId()))
                .append(format("<tr><td colspan='2'><a href='%s/shelter/close/%d?result=true'>Accept Request</td></tr>", baseUrl, adoptionRequest.getId()))
                .append("</table>")
                .append("</div>");
        getJspContext().getOut().print(sb.toString());
    }
}
