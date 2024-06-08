package de.fhws.fiw.fds.partnerUniversityManagement.server.database.utils;

import de.fhws.fiw.fds.partnerUniversityManagement.server.database.DaoFactory;
public class ResetDatabase {
    public static void resetAll() {
        DaoFactory.getInstance().getPartnerUniversityDao().resetDatabase();
    }
}
