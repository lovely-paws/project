package edu.johnshopkins.lovelypaws.tags;

import edu.johnshopkins.lovelypaws.entity.AnimalType;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

public class AnimalTypeTag extends SimpleTagSupport {

    private AnimalType animalType;
    public void setAnimalType(AnimalType animalType){ this.animalType = animalType; }

    private String baseUrl;
    public void setBaseUrl(String baseUrl){ this.baseUrl = baseUrl; }

    public void doTag() throws JspTagException, IOException {
        if(animalType == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<table>")
                .append(String.format("<tr><td>Name</td><td>%s</td></tr>", animalType.getName()))
                .append(String.format("<tr><td>Description</td><td>%s</td></tr>", animalType.getDescription()))
                .append(String.format("<tr><td colspan='2'><a href='%s/animal-type/edit/%d'>Edit</a></td></tr>",
                        baseUrl, animalType.getId()))
                .append("</table>");
        getJspContext().getOut().print(sb.toString());
    }
}
