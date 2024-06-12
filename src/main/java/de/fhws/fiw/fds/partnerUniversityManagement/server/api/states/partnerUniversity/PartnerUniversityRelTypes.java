package de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversity;

public interface PartnerUniversityRelTypes {

    String CREATE_PARTNER_UNIVERSITY = "createPartnerUniversity";
    String GET_ALL_PARTNER_UNIVERSITIES = "getAllPartnerUniversities";
    String GET_ALL_PARTNER_UNIVERSITIES_BY_NAME_AND_COUNTRY = "getAllPartnersByNameAndCountry";
    String GET_ALL_PARTNER_UNIVERSITIES_BY_NAME_AND_COUNTRY_ASC = "getAllPartnersByNameAndCountryAscending";
    String GET_ALL_PARTNER_UNIVERSITIES_BY_NAME_AND_COUNTRY_DESC = "getAllPartnersByNameAndCountryDescending";
    String GET_SINGLE_PARTNER_UNIVERSITY = "getPartnerUniversity";
    String UPDATE_SINGLE_PARTNER_UNIVERSITY = "updatePartnerUniversity";
    String DELETE_SINGLE_PARTNER_UNIVERSITY = "deletePartnerUniversity";
}
