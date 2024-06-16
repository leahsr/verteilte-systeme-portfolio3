package de.fhws.fiw.fds.partneruniversitymanagement.server.api.services;

import de.fhws.fiw.fds.partneruniversitymanagement.server.api.models.Module;
import de.fhws.fiw.fds.partneruniversitymanagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.partneruniversitymanagement.server.api.queries.QueryByModulePartnerUni;
import de.fhws.fiw.fds.partneruniversitymanagement.server.api.queries.QueryByNameAndCountryAndOrder;
import de.fhws.fiw.fds.partneruniversitymanagement.server.api.states.partnerUniversity.*;
import de.fhws.fiw.fds.partneruniversitymanagement.server.api.states.partnerUniversityModule.*;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractJerseyService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("partnerUniversities")
public class PartnerUniversityJerseyService extends AbstractJerseyService {
    public PartnerUniversityJerseyService() {
        super();
    }

    //Partner Unis

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPartnerUniversities(
            @DefaultValue("") @QueryParam("name") final String name,
            @DefaultValue("") @QueryParam("country") final String country,
            @DefaultValue("") @QueryParam("order") final String order,
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("20") @QueryParam("size") int size) {
        try {
            return new GetAllPartnerUniversities(
                    this.serviceContext,
                    new QueryByNameAndCountryAndOrder<>(
                            name, country, order,offset, size)
            ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(e.getExceptionMessage(), e.getStatus().getCode());
        }
    }

    @GET
    @Path("{id: \\d+}")
    @Produces({MediaType.APPLICATION_JSON})
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
            partnerUniversityModel.validate();
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
            partnerUniversityModel.validate();
            return new PutSinglePartnerUniversity(this.serviceContext, id, partnerUniversityModel).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteSinglePartnerUniversity(@PathParam("id") final long id) {
        try {
            return new DeleteSinglePartnerUniversity(this.serviceContext, id).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode()).entity(e.getExceptionMessage()).build());
        }
    }

    // Modules of Partner Unis

    @GET
    @Path("{partnerUniId: \\d+}/modules")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getModulesOfPartnerUni(
            @PathParam("partnerUniId") final long partnerUniId,
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("20") @QueryParam("size") int size) {
        try {
            return new GetAllModulesOfPartnerUniversity(this.serviceContext, partnerUniId, new QueryByModulePartnerUni<>(partnerUniId, offset, size)).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{partnerUniId: \\d+}/modules/{moduleId: \\d+}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getModuleByIdOfPartnerUni(
            @PathParam("partnerUniId") final long partnerUniId,
            @PathParam("moduleId") final long moduleId
    ) {
        try {
            return new GetSingleModuleOfPartnerUniversity(this.serviceContext, partnerUniId, moduleId).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode()).entity(e.getExceptionMessage()).build());
        }
    }

    @POST
    @Path("{partnerUniId: \\d+}/modules")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createNewModuleOfPartnerUniversity(@PathParam("partnerUniId") final long partnerUniId, final Module module) {
        try {
            module.validate();
            return new PostModulePartnerUniversity(this.serviceContext, partnerUniId, module).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{partnerUniId: \\d+}/modules/{moduleId: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateModuleOfPartnerUni(
            @PathParam("partnerUniId") final long partnerUniId,
            @PathParam("moduleId") final long moduleId,
            final Module module
    ) {
        try {
            module.validate();
            return new PutModulePartnerUniversity(this.serviceContext, partnerUniId, moduleId, module).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{partnerUniId: \\d+}/modules/{moduleId: \\d+}")
    public Response deleteModuleOfPartnerUni(
            @PathParam("partnerUniId") final long partnerUniId,
            @PathParam("moduleId") final long moduleId
    ) {
        try {
            return new DeleteSingleModulePartnerUniversity(this.serviceContext, moduleId, partnerUniId).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }
}
