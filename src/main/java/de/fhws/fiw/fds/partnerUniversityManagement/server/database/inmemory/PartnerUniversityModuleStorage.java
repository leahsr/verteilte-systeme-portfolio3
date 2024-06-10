package de.fhws.fiw.fds.partnerUniversityManagement.server.database.inmemory;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.Module;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.ModuleDao;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.PartnerUniversityModuleDao;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class PartnerUniversityModuleStorage extends AbstractInMemoryRelationStorage<Module> implements PartnerUniversityModuleDao {

    final private ModuleDao moduleStorage;

    public PartnerUniversityModuleStorage(ModuleDao moduleStorage) {
        this.moduleStorage = moduleStorage;
    }

    @Override
    protected IDatabaseAccessObject<Module> getSecondaryStorage() {
        return this.moduleStorage;
    }

    @Override
    public CollectionModelResult<Module> readAllLinked(long primaryId, SearchParameter searchParameter) {
        return InMemoryPaging.page(
                this.readAllLinkedByPredicate(primaryId, (p) -> true),
                searchParameter.getOffset(), searchParameter.getSize()
        );
    }

    @Override
    public void resetDatabase() {
        this.storage.clear();
    }

}

