package de.fhws.fiw.fds.partnerUniversityManagement.client.web;

import de.fhws.fiw.fds.partnerUniversityManagement.client.models.DispatcherModel;
import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;

import java.io.IOException;

public class DispatcherWebClient {

    private GenericWebClient<DispatcherModel> client;

    public DispatcherWebClient() {
        this.client = new GenericWebClient<>();
    }

    private DispatcherWebResponse getDispatcher(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url));
    }

    private DispatcherWebResponse createResponse(WebApiResponse<DispatcherModel> response) throws IOException {
        return new DispatcherWebResponse(
                response.getResponseData(),
                response.getResponseHeaders(),
                response.getLastStatusCode());
    }

    public DispatcherWebResponse resetDatabaseOnServer(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url + "/resetdatabase"));
    }
}
