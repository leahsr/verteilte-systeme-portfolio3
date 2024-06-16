package de.fhws.fiw.fds.partneruniversitymanagement.server.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SecondarySelfLink;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SelfLink;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.Status;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

@JsonRootName("module")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Module extends AbstractModel {

    private String name;
    private int semester;
    private float ects;

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
        this.semester = semester;
        this.ects = ects;
    }

    public void validate() throws SuttonWebAppException {
        if (!(this.semester == 1 || this.semester == 2)) throw new SuttonWebAppException(Status.BAD_REQUEST,
                "only 1 (spring) and 2 (autumn) are valid semester types");
        if(this.ects < 0) throw new SuttonWebAppException(Status.BAD_REQUEST,
                "ects must be greater 0");
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

    public float getEcts() {
        return ects;
    }

    public void setEcts(float ects) {
        this.ects = ects;
    }

    public Link getSelfLinkOnSecond() {
        return selfLinkOnSecond;
    }

    public Link getSelfLink() {
        return selfLink;
    }
}
