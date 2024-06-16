package de.fhws.fiw.fds.partneruniversitymanagement.server.api.states.partnerUniversityModule;

import de.fhws.fiw.fds.partneruniversitymanagement.server.api.models.Module;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;
import jakarta.ws.rs.core.Response;

public class GetAllModulesOfPartnerUniversity extends AbstractGetCollectionRelationState<Response, Module> {
    public GetAllModulesOfPartnerUniversity(ServiceContext serviceContext, long primaryId, AbstractRelationQuery<Response, Module> query) {
        super(serviceContext, primaryId, query);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerUniversityModuleUri.REL_PATH,
                PartnerUniversityModuleRelTypes.CREATE_MODULE,
                getAcceptRequestHeader(), this.primaryId);
    }
}
