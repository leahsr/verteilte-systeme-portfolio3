package de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.dispatcher;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;
import jakarta.ws.rs.core.Response;

public class GetDispatcher extends AbstractGetDispatcherState<Response> {
    public GetDispatcher(ServiceContext serviceContext) {
        super(serviceContext);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {
        //TODO
        //addLink(PartnerUniversityUri)
    }
}
