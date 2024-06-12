package de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversity;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import jakarta.ws.rs.core.Response;

public class GetAllPartnerUniversities extends AbstractGetCollectionState<Response, PartnerUniversity> {

    public GetAllPartnerUniversities(ServiceContext serviceContext, AbstractQuery<Response, PartnerUniversity> query) {
        super(serviceContext, query);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerUniversityUri.REL_PATH, PartnerUniversityRelTypes.CREATE_PARTNER_UNIVERSITY, getAcceptRequestHeader());
        addLink(PartnerUniversityUri.REL_PATH + "?name={NAME}&country" +
                "={COUNTRY}",
                PartnerUniversityRelTypes.GET_ALL_PARTNER_UNIVERSITIES_BY_NAME_AND_COUNTRY, getAcceptRequestHeader()
        );
        addLink(PartnerUniversityUri.REL_PATH +
                "?name={NAME}&country={COUNTRY}&order=ASC",
                PartnerUniversityRelTypes.GET_ALL_PARTNER_UNIVERSITIES_BY_NAME_AND_COUNTRY_ASC, getAcceptRequestHeader());
        addLink(PartnerUniversityUri.REL_PATH +
                        "?name={NAME}&country={COUNTRY}&order=DESC",
                PartnerUniversityRelTypes.GET_ALL_PARTNER_UNIVERSITIES_BY_NAME_AND_COUNTRY_DESC, getAcceptRequestHeader());
    }
}
