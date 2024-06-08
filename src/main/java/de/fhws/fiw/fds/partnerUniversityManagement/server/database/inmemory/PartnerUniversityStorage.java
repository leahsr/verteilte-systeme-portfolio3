package de.fhws.fiw.fds.partnerUniversityManagement.server.database.inmemory;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.PartnerUniversityDao;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class PartnerUniversityStorage extends AbstractInMemoryStorage<PartnerUniversity> implements PartnerUniversityDao {

    @Override
    public void resetDatabase() {
    }

    @Override
    public CollectionModelResult<PartnerUniversity> readAll() {
        return PartnerUniversityDao.super.readAll();
    }
}
