package de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversities;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import jakarta.ws.rs.core.Response;

public class DeleteSinglePartnerUniversity extends AbstractDeleteState<Response, PartnerUniversity> {
    public DeleteSinglePartnerUniversity(ServiceContext serviceContext, long modelIdToDelete) {
        super(serviceContext, modelIdToDelete);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<PartnerUniversity> loadModel() {
        return DaoFactory.getInstance().getPartnerUniversityDao().readById(this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getPartnerUniversityDao().delete(this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerUniversityUri.REL_PATH,
                PartnerUniversityRelTypes.GET_ALL_PARTNER_UNIVERSITIES,
                getAcceptRequestHeader());
    }
}
