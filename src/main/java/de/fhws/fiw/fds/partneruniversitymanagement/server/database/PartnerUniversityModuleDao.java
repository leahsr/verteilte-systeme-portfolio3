package de.fhws.fiw.fds.partneruniversitymanagement.server.database;

import de.fhws.fiw.fds.partneruniversitymanagement.server.api.models.Module;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;

public interface PartnerUniversityModuleDao extends IDatabaseRelationAccessObject<Module> {


    void resetDatabase();
}
