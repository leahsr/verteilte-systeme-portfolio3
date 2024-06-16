package de.fhws.fiw.fds.partneruniversitymanagement.server.database;

import de.fhws.fiw.fds.partneruniversitymanagement.server.api.models.Module;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;

public interface ModuleDao extends IDatabaseAccessObject<Module> {
    void resetDatabase();
}
