package de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversityModule;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.Module;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.ws.rs.core.Response;

public class PostModulePartnerUniversity extends AbstractPostRelationState<Response, Module> {
    public PostModulePartnerUniversity(ServiceContext serviceContext, long primaryId, Module modelToStore) {
        super(serviceContext, primaryId, modelToStore);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().getPartnerUniversityModuleDao().create(this.primaryId, this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {
        //TODO: check if necessary
        addLink(PartnerUniversityModuleUri.REL_PATH_ID,
                PartnerUniversityModuleRelTypes.GET_SINGLE_MODULE_OF_PARTNER_UNI, this.primaryId);
    }
}
