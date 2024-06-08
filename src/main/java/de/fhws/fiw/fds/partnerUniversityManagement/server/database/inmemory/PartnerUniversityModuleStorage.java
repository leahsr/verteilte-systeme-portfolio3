package de.fhws.fiw.fds.partnerUniversityManagement.server.database.inmemory;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.Module;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.ModuleDao;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.PartnerUniversityModuleDao;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class PartnerUniversityModuleStorage extends AbstractInMemoryStorage<Module> implements PartnerUniversityModuleDao {
    final private ModuleDao moduleStorage;

    public PartnerUniversityModuleStorage(ModuleDao moduleStorage) {
        this.moduleStorage = moduleStorage;
    }

    @Override
    public NoContentResult create(long primaryId, Module secondary) {
        return null;
    }

    @Override
    public NoContentResult update(long primaryId, Module secondary) {
        return null;
    }

    @Override
    public NoContentResult deleteRelation(long primaryId, long secondaryId) {
        return null;
    }

    @Override
    public NoContentResult deleteRelationsFromPrimary(long primaryId) {
        return null;
    }

    @Override
    public NoContentResult deleteRelationsToSecondary(long secondaryId) {
        return null;
    }

    @Override
    public SingleModelResult<Module> readById(long primaryId, long secondaryId) {
        return null;
    }

    @Override
    public CollectionModelResult<Module> readAllLinked(long primaryId, SearchParameter searchParameter) {
        return null;
    }
}
