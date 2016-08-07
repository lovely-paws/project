package edu.johnshopkins.lovelypaws.tags;

import edu.johnshopkins.lovelypaws.Role;
import edu.johnshopkins.lovelypaws.entity.Administrator;
import edu.johnshopkins.lovelypaws.entity.EndUser;
import edu.johnshopkins.lovelypaws.entity.Shelter;
import edu.johnshopkins.lovelypaws.entity.User;
import static java.lang.String.format;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class UserTag extends SimpleTagSupport {
    private User user;
    public void setUser(User user){ this.user = user; }

    private User viewer;
    public void setViewer(User viewer) { this.viewer = viewer;}

    private String baseUrl;
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }

    private boolean detailed;
    public void setDetailed(boolean detailed) { this.detailed = detailed; }

    public void doTag() throws JspTagException, IOException {
        StringBuilder sb = new StringBuilder();
        if(user == null) {
            return;
        }

        boolean isShelter = user.getRole() == Role.SHELTER;
        boolean isEndUser = user.getRole() == Role.END_USER;

        sb.append("<div>\n")
                .append("<table>\n")
                .append(format("<tr><td colspan='2'>[%s #%d] %s</td></tr>\n", user.getRole(), user.getId(), user.getUsername()));
        if(isShelter) {
            Shelter casted = (Shelter)user;
            if(!detailed) {
                sb.append(String.format("<tr><td>Detailed View</td><td><a href='%s/shelter/view/%d'>Click</a></td></tr>\n",
                        baseUrl, casted.getId()));
            }
            sb.append("<tr><td>Business Name</td><td>").append(casted.getName()).append("</td></tr>\n");
            sb.append("<tr><td>Address</td><td>").append(casted.getAddress()).append("</td></tr>\n");
            sb.append("<tr><td>Description</td><td>").append(casted.getDescription()).append("</td></tr>\n");
            sb.append("<tr><td>Phone Number</td><td>").append(casted.getPhoneNumber()).append("</td></tr>\n");
        } else if(isEndUser) {
            EndUser casted = (EndUser)user;
            sb.append(String.format("<tr><td>Name</td><td>%s</td></tr>\n",
                    casted.getName()));
        }
        sb.append("<tr><td>E-Mail Address</td><td>").append(user.getEmailAddress()).append("</td></tr>");
        if(viewer != null) {
            switch(viewer.getRole()) {
                case ADMINISTRATOR:
                    sb.append(String.format("<tr><td colspan='2'><a href='%s/user/delete/%d'>Delete User</a></td></tr>\n", baseUrl, user.getId()));
                    sb.append(String.format("<tr><td colspan='2'><a href='%s/user/edit/%d'>Edit User</a></td></tr>\n", baseUrl, user.getId()));
                    if(isShelter) {
                        sb.append("<tr><td>Actions</td><td><ul>");
                        Shelter casted = (Shelter)user;
                        sb.append(String.format("<li><a href='%s/shelter/%s/%d'>%s</a></li>",
                                baseUrl, casted.isApproved() ? "deny" : "approve", casted.getId(), casted.isApproved() ? "Disable New Listings" : "Enable New Listings"));
                        sb.append(String.format("<li><a href='%s/shelter/requests/%d'>Review Adoption Requests</a></li>",
                                baseUrl, casted.getId()));
                        sb.append("</ul></td></tr>");
                    }
                    break;
                case SHELTER:
                    break;
                case END_USER:
                    break;
                default:
                    break;
            }
        }
        sb.append("</table>");
        sb.append("</div>");
        getJspContext().getOut().print(sb.toString());
    }
}
