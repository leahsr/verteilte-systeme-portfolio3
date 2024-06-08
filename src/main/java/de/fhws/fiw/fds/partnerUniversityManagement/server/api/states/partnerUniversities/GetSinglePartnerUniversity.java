package de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversities;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.states.persons.PersonRelTypes;
import jakarta.ws.rs.core.Response;

public class GetSinglePartnerUniversity extends AbstractGetState<Response,
        PartnerUniversity> {

    public GetSinglePartnerUniversity(ServiceContext serviceContext, long requestedId) {
        super(serviceContext, requestedId);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<PartnerUniversity> loadModel() {
        return DaoFactory.getInstance().getPartnerUniversityDao().readById(this.requestedId);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerUniversityUri.REL_PATH_ID,
                PersonRelTypes.UPDATE_SINGLE_PERSON, getAcceptRequestHeader()
                , this.requestedId);
        addLink(PartnerUniversityUri.REL_PATH_ID,
                PersonRelTypes.DELETE_SINGLE_PERSON, getAcceptRequestHeader()
                , this.requestedId);
    }
}
