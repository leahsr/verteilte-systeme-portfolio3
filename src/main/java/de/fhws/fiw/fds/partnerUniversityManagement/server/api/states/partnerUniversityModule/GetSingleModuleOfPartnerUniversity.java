package de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversityModule;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.Module;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import jakarta.ws.rs.core.Response;

public class GetSingleModuleOfPartnerUniversity extends AbstractGetRelationState<Response, Module> {

    public GetSingleModuleOfPartnerUniversity(ServiceContext serviceContext, long primaryId, long requestedId) {
        super(serviceContext, primaryId, requestedId);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<Module> loadModel() {
        SingleModelResult<Module> module =
                DaoFactory.getInstance().getModuleDao().readById(this.requestedId);
        if(isModuleLinkedToThisPartnerUniversity()) {
            module.getResult().setPrimaryId(this.primaryId);
        }
        return module;
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerUniversityModuleUri.REL_PATH,
                PartnerUniversityModuleRelTypes.GET_ALL_MODULES_OF_PARTNER_UNI, getAcceptRequestHeader(), this.primaryId);

        addLink(PartnerUniversityModuleUri.REL_PATH_ID,
                PartnerUniversityModuleRelTypes.UPDATE_SINGLE_MODULE_OF_PARTNER_UNI, getAcceptRequestHeader(), this.primaryId, this.requestedId);

        addLink(PartnerUniversityModuleUri.REL_PATH_ID,
                PartnerUniversityModuleRelTypes.DELETE_SINGLE_MODULE_OF_PARTNER_UNI, getAcceptRequestHeader(), this.primaryId, this.requestedId);
    }

    private boolean isModuleLinkedToThisPartnerUniversity() {
        return !DaoFactory.getInstance().getPartnerUniversityModuleDao().readById(this.primaryId, this.requestedId).isEmpty();
    }
}
