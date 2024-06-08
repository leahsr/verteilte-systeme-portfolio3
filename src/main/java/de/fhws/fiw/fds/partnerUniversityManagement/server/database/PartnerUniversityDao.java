package de.fhws.fiw.fds.partnerUniversityManagement.server.database;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;

public interface PartnerUniversityDao extends IDatabaseAccessObject<PartnerUniversity> {

    void resetDatabase();
}
