package de.fhws.fiw.fds.partneruniversitymanagement;

import de.fhws.fiw.fds.partneruniversitymanagement.server.api.services.DispatcherJerseyService;
import de.fhws.fiw.fds.partneruniversitymanagement.server.api.services.PartnerUniversityJerseyService;
import de.fhws.fiw.fds.sutton.server.api.AbstractJerseyApplication;
import jakarta.ws.rs.ApplicationPath;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class SuttonPartnerUniversityJerseyApplication extends AbstractJerseyApplication {
    @Override
    protected Set<Class<?>> getServiceClasses() {
        System.out.println("Käse2");
        final Set<Class<?>> returnValue = new HashSet<>();

        returnValue.add(DispatcherJerseyService.class);
        returnValue.add(PartnerUniversityJerseyService.class);

        return returnValue;
    }
}
