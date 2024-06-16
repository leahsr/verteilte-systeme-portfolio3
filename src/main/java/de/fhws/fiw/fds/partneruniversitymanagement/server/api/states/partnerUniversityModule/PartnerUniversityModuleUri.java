package de.fhws.fiw.fds.partneruniversitymanagement.server.api.states.partnerUniversityModule;


import de.fhws.fiw.fds.partneruniversitymanagement.server.api.states.partnerUniversity.PartnerUniversityUri;

public interface PartnerUniversityModuleUri {
    String PATH_ELEMENT = "modules";
    String REL_PATH = PartnerUniversityUri.REL_PATH_ID + "/" +PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
}
