package de.fhws.fiw.fds.partnerUniversityManagement.server.database;

import de.fhws.fiw.fds.partnerUniversityManagement.server.database.inmemory.ModuleStorage;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.inmemory.PartnerUniversityModuleStorage;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.inmemory.PartnerUniversityStorage;

public class DaoFactory {

    private static DaoFactory INSTANCE;

    public static DaoFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DaoFactory();
        }
        return INSTANCE;
    }

    private final PartnerUniversityDao partnerUniversityDao;
    private final ModuleDao moduleDao;
    private final PartnerUniversityModuleDao partnerUniversityModuleDao;

    private DaoFactory() {
        this.partnerUniversityDao = new PartnerUniversityStorage();
        this.moduleDao = new ModuleStorage();
        this.partnerUniversityModuleDao = new PartnerUniversityModuleStorage(this.moduleDao);
    }

    public PartnerUniversityDao getPartnerUniversityDao() {
        return this.partnerUniversityDao;
    }

    public ModuleDao getModuleDao() {
        return this.moduleDao;
    }

    public PartnerUniversityModuleDao getPartnerUniversityModuleDao() {
        return this.partnerUniversityModuleDao;
    }
}
