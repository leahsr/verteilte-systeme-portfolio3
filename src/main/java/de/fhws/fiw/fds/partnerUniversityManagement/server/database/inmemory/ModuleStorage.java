package de.fhws.fiw.fds.partnerUniversityManagement.server.database.inmemory;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.Module;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.ModuleDao;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public class ModuleStorage extends AbstractInMemoryStorage<Module> implements ModuleDao {
    @Override
    public NoContentResult create(Module model) {
        return null;
    }

    @Override
    public CollectionModelResult<Module> readAll() {
        return ModuleDao.super.readAll();
    }

    @Override
    public NoContentResult update(Module model) {
        return null;
    }
}
