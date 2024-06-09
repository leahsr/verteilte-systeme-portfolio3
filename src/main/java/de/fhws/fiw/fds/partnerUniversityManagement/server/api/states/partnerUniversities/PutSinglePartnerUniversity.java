package de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversities;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import jakarta.ws.rs.core.Response;

public class PutSinglePartnerUniversity extends AbstractPutState<Response,
        PartnerUniversity> {

    public PutSinglePartnerUniversity(ServiceContext serviceContext, long requestId, PartnerUniversity modelToUpdate) {
        super(serviceContext, requestId, modelToUpdate);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<PartnerUniversity> loadModel() {
        return DaoFactory.getInstance().getPartnerUniversityDao().readById(this.modelToUpdate.getId());
    }

    @Override
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().getPartnerUniversityDao().update(this.modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerUniversityUri.REL_PATH_ID,
                PartnerUniversityRelTypes.GET_SINGLE_PARTNER_UNIVERSITY,
                getAcceptRequestHeader(), this.modelToUpdate.getId());
    }
}
