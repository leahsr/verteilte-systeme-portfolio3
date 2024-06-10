package de.fhws.fiw.fds.partnerUniversityManagement.server.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SecondarySelfLink;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SelfLink;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.xml.bind.annotation.XmlRootElement;

@JsonRootName("module")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Module extends AbstractModel {

    private String name;
    private int semester;
    private int ects;

    @SecondarySelfLink(
            primaryPathElement = "partnerUniversities",
            secondaryPathElement = "modules"
    )
    private transient Link selfLinkOnSecond;

    @SelfLink(pathElement = "modules")
    private transient Link selfLink;

    public Module() {
        //make JPA happy
    }

    public Module(String name, int semester, int ects) {
        this.name = name;
        this.semester = Math.abs(semester);
        this.ects = ects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }
}
