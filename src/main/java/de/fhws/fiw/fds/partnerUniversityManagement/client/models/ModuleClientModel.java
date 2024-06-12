package de.fhws.fiw.fds.partnerUniversityManagement.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.Link;

public class ModuleClientModel extends AbstractClientModel {
    private String name;
    private int semester;
    private float ects;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link selfLink;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link module;

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

    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }

    public Link getModule() {
        return module;
    }

    public void setModule(Link module) {
        this.module = module;
    }
}
