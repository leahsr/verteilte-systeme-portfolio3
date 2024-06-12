package de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversity;

import de.fhws.fiw.fds.partnerUniversityManagement.Start;

public interface PartnerUniversityUri {

    String PATH_ELEMENT = "partnerUniversities";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" +PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
}
