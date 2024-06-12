package de.fhws.fiw.fds.partnerUniversityManagement.client.web;

import de.fhws.fiw.fds.partnerUniversityManagement.client.models.PartnerUniversityClientModel;
import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;

import java.io.IOException;

public class PartnerUniversityWebClient {
    private GenericWebClient<PartnerUniversityClientModel> client;

    public PartnerUniversityWebClient() {
        this.client = new GenericWebClient<>();
    }

    public PartnerUniversityWebResponse getCollectionOfPartnerUniversities(String url) throws IOException {
        return createResponse(this.client.sendGetCollectionRequest(url, PartnerUniversityClientModel.class));
    }

    public PartnerUniversityWebResponse getSinglePartnerUniversity(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url, PartnerUniversityClientModel.class));
    }

    public PartnerUniversityWebResponse postNewPartnerUniversity(String url,
                                                                 PartnerUniversityClientModel partnerUni) throws IOException {
        return createResponse(this.client.sendPostRequest(url, partnerUni));
    }

    public PartnerUniversityWebResponse putPartnerUniversity(String url,
                                                             PartnerUniversityClientModel partnerUni) throws IOException {
        return createResponse(this.client.sendPutRequest(url, partnerUni));
    }

    public PartnerUniversityWebResponse deletePartnerUniversity(String url) throws IOException {
        return createResponse(this.client.sendDeleteRequest(url));
    }

    private PartnerUniversityWebResponse createResponse(WebApiResponse<PartnerUniversityClientModel> response) {
        return new PartnerUniversityWebResponse(
                response.getResponseData(),
                response.getResponseHeaders(),
                response.getLastStatusCode()
        );
    }
}
