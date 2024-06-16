package de.fhws.fiw.fds.partneruniversitymanagement.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.Link;

import java.time.LocalDate;

public class PartnerUniversityClientModel extends AbstractClientModel {
    private String name;
    private String country;
    private String department;
    private String website;
    private String contactPerson;
    private int numberOfStudentsSend;
    private int numberOfStudentsReceive;
    private LocalDate firstDaySpringSemester;
    private LocalDate firstDayAutumnSemester;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link selfLink;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link modules;

    public PartnerUniversityClientModel() {}

    public PartnerUniversityClientModel(final String name, final String country, final String department, final String website, final String contactPerson, final int numberOfStudentsSend, final int numberOfStudentsReceive, final LocalDate firstDaySpringSemester, final LocalDate firstDayAutumnSemester) {
        this.name = name;
        this.country = country;
        this.department = department;
        this.website = website;
        this.contactPerson = contactPerson;
        this.numberOfStudentsSend = numberOfStudentsSend;
        this.numberOfStudentsReceive = numberOfStudentsReceive;
        this.firstDaySpringSemester = firstDaySpringSemester;
        this.firstDayAutumnSemester = firstDayAutumnSemester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public int getNumberOfStudentsSend() {
        return numberOfStudentsSend;
    }

    public void setNumberOfStudentsSend(int numberOfStudentsSend) {
        this.numberOfStudentsSend = numberOfStudentsSend;
    }

    public int getNumberOfStudentsReceive() {
        return numberOfStudentsReceive;
    }

    public void setNumberOfStudentsReceive(int numberOfStudentsReceive) {
        this.numberOfStudentsReceive = numberOfStudentsReceive;
    }

    public LocalDate getFirstDaySpringSemester() {
        return firstDaySpringSemester;
    }

    public void setFirstDaySpringSemester(LocalDate firstDaySpringSemester) {
        this.firstDaySpringSemester = firstDaySpringSemester;
    }

    public LocalDate getFirstDayAutumnSemester() {
        return firstDayAutumnSemester;
    }

    public void setFirstDayAutumnSemester(LocalDate firstDayAutumnSemester) {
        this.firstDayAutumnSemester = firstDayAutumnSemester;
    }

    @JsonIgnore
    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }

    @JsonIgnore
    public Link getModules() {
        return modules;
    }

    public void setModules(Link modules) {
        this.modules = modules;
    }
}
