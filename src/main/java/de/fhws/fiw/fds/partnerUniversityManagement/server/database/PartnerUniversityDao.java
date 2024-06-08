package de.fhws.fiw.fds.partnerUniversityManagement.server.database;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface PartnerUniversityDao extends IDatabaseAccessObject<PartnerUniversity> {

    public CollectionModelResult<PartnerUniversity> readByNameAndCountry(String name, String country, SearchParameter searchParameter);
    void resetDatabase();
}
