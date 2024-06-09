package de.fhws.fiw.fds.partnerUniversityManagement.server.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SuttonLink;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;
import java.util.Date;
@JsonRootName("partnerUniversity")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerUniversity extends AbstractModel  {

    private String name;
    private String country;
    private String department;
    private String website;
    private String contactPerson;
    private int numberOfStudentsSend;
    private int numberOfStudentsReceive;
    private LocalDate firstDaySpringSemester;
    private LocalDate firstDayAutumnSemester;
    private Link modules;

    @SuttonLink(
            value = "partnerUniversities/${id}",
            rel = "self"
    )
    private transient Link selfLink;

    public PartnerUniversity() {
        //make JPA happy
    }

    public PartnerUniversity(String name, String country, String department, String website, String contactPerson, int numberOfStudentsSend, int numberOfStudentsReceive, LocalDate firstDaySpringSemester, LocalDate firstDayAutumnSemester) {
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

    @Override
    public String toString() {
        return "PartnerUniversity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", department='" + department + '\'' +
                ", website='" + website + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", numberOfStudentsSend=" + numberOfStudentsSend+
                ", numberOfStudentsReceive=" + numberOfStudentsReceive +
                ", firstDaySpringSemester=" + firstDaySpringSemester +
                ", firstDayAutumnSemester=" + firstDayAutumnSemester +
                ", modules =" + modules +
                '}';
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

    public Link getModules() {
        return modules;
    }

    public void setModules(Link modules) {
        this.modules = modules;
    }

    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }
}
