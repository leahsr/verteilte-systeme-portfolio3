package de.fhws.fiw.fds.partnerUniversityManagement;

import de.fhws.fiw.fds.sutton.server.api.AbstractJerseyApplication;
import de.fhws.fiw.fds.suttondemo.server.api.services.DispatcherJerseyService;
import de.fhws.fiw.fds.suttondemo.server.api.services.LocationJerseyService;
import de.fhws.fiw.fds.suttondemo.server.api.services.PersonJerseyService;
import jakarta.ws.rs.ApplicationPath;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class PartnerUniversityJerseryApplication extends AbstractJerseyApplication {
    @Override
    protected Set<Class<?>> getServiceClasses() {
        final Set<Class<?>> returnValue = new HashSet<>();

        returnValue.add(PersonJerseyService.class);
        returnValue.add(LocationJerseyService.class);
        returnValue.add(DispatcherJerseyService.class);

        return returnValue;
    }
}
