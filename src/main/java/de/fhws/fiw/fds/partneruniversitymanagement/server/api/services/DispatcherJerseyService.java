package de.fhws.fiw.fds.partneruniversitymanagement.server.api.services;

import de.fhws.fiw.fds.partneruniversitymanagement.server.api.states.dispatcher.GetDispatcher;
import de.fhws.fiw.fds.partneruniversitymanagement.server.database.utils.ResetDatabase;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractJerseyService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("")
public class DispatcherJerseyService extends AbstractJerseyService {

    @GET
    public Response getDispatcher() {
        try {
            return new GetDispatcher(this.serviceContext) .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("resetdatabase")
    @Produces({MediaType.APPLICATION_JSON})
    public Response resetDatabase() {
        System.out.println("Resetting database");
        ResetDatabase.resetAll();
        return Response.ok().build();
    }

}
