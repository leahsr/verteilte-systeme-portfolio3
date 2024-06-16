package de.fhws.fiw.fds.partneruniversitymanagement.server.database.inmemory;

import de.fhws.fiw.fds.partneruniversitymanagement.server.api.models.Module;
import de.fhws.fiw.fds.partneruniversitymanagement.server.database.ModuleDao;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;

public class ModuleStorage extends AbstractInMemoryStorage<Module> implements ModuleDao {
    @Override
    public void resetDatabase() {
        this.storage.clear();
    }
}
