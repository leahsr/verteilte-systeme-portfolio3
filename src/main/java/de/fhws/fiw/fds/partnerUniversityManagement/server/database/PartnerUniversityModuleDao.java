package de.fhws.fiw.fds.partnerUniversityManagement.server.database;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.Module;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface PartnerUniversityModuleDao extends IDatabaseRelationAccessObject<Module> {


    void resetDatabase();
}
