package de.fhws.fiw.fds.partnerUniversityManagement.server.api.services;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.partnerUniversityManagement.server.api.queries.QueryByNameAndCountry;
import de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversities.GetAllPartnerUniversities;
import de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversities.GetSinglePartnerUniversity;
import de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversities.PostNewPartnerUniversity;
import de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversities.PutSinglePartnerUniversity;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractJerseyService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class PartnerUniversityJerseyService extends AbstractJerseyService {
    public PartnerUniversityJerseyService() {
        super();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllPartnerUniversities(
            @DefaultValue("") @QueryParam("name") final String name,
            @DefaultValue("") @QueryParam("country") final String country,
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("20") @QueryParam("size") int size) {
        try {
            return new GetAllPartnerUniversities(
                    this.serviceContext,
                    new QueryByNameAndCountry<>(name, country, offset, size)
            ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(e.getExceptionMessage(), e.getStatus().getCode());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSinglePartnerUniversity(@PathParam("id") final long id) {
        try {
            return new GetSinglePartnerUniversity(this.serviceContext, id).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response
                    .status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build()
            );
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createSinglePartnerUniversity(final PartnerUniversity partnerUniversityModel) {
        try {
            return new PostNewPartnerUniversity(this.serviceContext, partnerUniversityModel).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateSinglePartnerUniversity(@PathParam("id") final long id, final PartnerUniversity partnerUniversityModel) {
        try {
            return new PutSinglePartnerUniversity(this.serviceContext, id, partnerUniversityModel).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

   /* @DELETE
    @Path("{id: \\d+}")
    @Consume*/
}
