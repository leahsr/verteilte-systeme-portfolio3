package de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversities;

import de.fhws.fiw.fds.partnerUniversityManagement.Start;

public interface PartnerUniversityUri {

    String PATH_ELEMENT = "partneruniversities";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" +PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
}