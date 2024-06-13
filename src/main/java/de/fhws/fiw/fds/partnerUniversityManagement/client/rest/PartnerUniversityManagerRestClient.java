package de.fhws.fiw.fds.partnerUniversityManagement.client.rest;

import de.fhws.fiw.fds.partnerUniversityManagement.PathUrls;
import de.fhws.fiw.fds.partnerUniversityManagement.client.models.ModuleClientModel;
import de.fhws.fiw.fds.partnerUniversityManagement.client.models.PartnerUniversityClientModel;
import de.fhws.fiw.fds.partnerUniversityManagement.client.web.DispatcherWebClient;
import de.fhws.fiw.fds.partnerUniversityManagement.client.web.ModuleWebClient;
import de.fhws.fiw.fds.partnerUniversityManagement.client.web.PartnerUniversityWebClient;
import de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversity.PartnerUniversityRelTypes;
import de.fhws.fiw.fds.partnerUniversityManagement.server.api.states.partnerUniversityModule.PartnerUniversityModuleRelTypes;
import de.fhws.fiw.fds.sutton.client.rest2.AbstractRestClient;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PartnerUniversityManagerRestClient extends AbstractRestClient {
    private static final String BASE_URL = PathUrls.BASE_URL;

    final private DispatcherWebClient dispatcherClient;
    final private PartnerUniversityWebClient partnerUniClient;
    final private ModuleWebClient moduleClient;

    private List<PartnerUniversityClientModel> currentPartnerUniversityData;
    private int cursorPartnerUniversityData = 0;

    public void setPartnerUniversityCursor(int index) {
        if(0 <= index && index < this.currentPartnerUniversityData.size()) {
            this.cursorPartnerUniversityData = index;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private List<ModuleClientModel> currentModuleData;
    private int cursorModuleData = 0;

    public void setModuleCursor(int index) {
        if(0 <= index && index < this.currentModuleData.size()) {
            this.cursorModuleData = index;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public PartnerUniversityManagerRestClient() {
        super();
        this.dispatcherClient = new DispatcherWebClient();
        this.partnerUniClient = new PartnerUniversityWebClient();
        this.moduleClient = new ModuleWebClient();
    }

    public void start() throws IOException {
        processResponse(this.dispatcherClient.getDispatcher(BASE_URL), (response -> {}));
    }

    public void resetDatabase() throws IOException {
        processResponse(this.dispatcherClient.resetDatabaseOnServer(BASE_URL), (response -> {}));
    }

    public boolean hasNext() {
        return isLinkAvailable("next");
    }

    public boolean hasPrevious() {
        return isLinkAvailable("previous");
    }

    public boolean isGetAllPartnerUniversitiesAllowed() {
        return isLinkAvailable(PartnerUniversityRelTypes.GET_ALL_PARTNER_UNIVERSITIES);
    }

    public void getAllPartnerUniversities() throws IOException {
        if(isGetAllPartnerUniversitiesAllowed()) {
            processResponse(this.partnerUniClient.getCollectionOfPartnerUniversities(getUrl(PartnerUniversityRelTypes.GET_ALL_PARTNER_UNIVERSITIES)),
                    (response -> {
                        this.currentPartnerUniversityData = new LinkedList<>(response.getResponseData());
                        this.cursorPartnerUniversityData = 0;
                    })
            );
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetAllPartnerUniversitiesByNameAndCountryAllowed() throws IOException {
        return isLinkAvailable(PartnerUniversityRelTypes.GET_ALL_PARTNER_UNIVERSITIES_BY_NAME_AND_COUNTRY);
    }

    public boolean isGetAllPartnerUniversitiesByNameAndCountryAscAllowed() throws IOException {
        return isLinkAvailable(PartnerUniversityRelTypes.GET_ALL_PARTNER_UNIVERSITIES_BY_NAME_AND_COUNTRY_ASC);
    }

    public boolean isGetAllPartnerUniversitiesByNameAndCountryDescAllowed() throws IOException {
        return isLinkAvailable(PartnerUniversityRelTypes.GET_ALL_PARTNER_UNIVERSITIES_BY_NAME_AND_COUNTRY_DESC);
    }

    public void getAllPartnerUniversitiesByNameAndCountry(String name, String country) throws IOException {
        if(isGetAllPartnerUniversitiesByNameAndCountryAllowed()) {
            String url = getUrl(PartnerUniversityRelTypes.GET_ALL_PARTNER_UNIVERSITIES_BY_NAME_AND_COUNTRY);
            url = url.replace("{NAME}", name);
            url = url.replace("{COUNTRY}", country);
            processResponse(this.partnerUniClient.getCollectionOfPartnerUniversities(url),
                    (response -> {
                        this.currentPartnerUniversityData = new LinkedList<>(response.getResponseData());
                        this.cursorPartnerUniversityData = 0;
                    })
            );
        } else {
            throw new IllegalStateException();
        }
    }

    public void getAllPartnerUniversitiesByNameAndCountryAsc(String name, String country) throws IOException {
        if(isGetAllPartnerUniversitiesByNameAndCountryAllowed()) {
            String url = getUrl(PartnerUniversityRelTypes.GET_ALL_PARTNER_UNIVERSITIES_BY_NAME_AND_COUNTRY_ASC);
            url = url.replace("{NAME}", name);
            url = url.replace("{COUNTRY}", country);
            processResponse(this.partnerUniClient.getCollectionOfPartnerUniversities(url),
                    (response -> {
                        this.currentPartnerUniversityData = new LinkedList<>(response.getResponseData());
                        this.cursorPartnerUniversityData = 0;
                    })
            );
        } else {
            throw new IllegalStateException();
        }
    }

    public void getAllPartnerUniversitiesByNameAndCountryDesc(String name, String country) throws IOException {
        if(isGetAllPartnerUniversitiesByNameAndCountryAllowed()) {
            String url = getUrl(PartnerUniversityRelTypes.GET_ALL_PARTNER_UNIVERSITIES_BY_NAME_AND_COUNTRY_DESC);
            url = url.replace("{NAME}", name);
            url = url.replace("{COUNTRY}", country);
            processResponse(this.partnerUniClient.getCollectionOfPartnerUniversities(url),
                    (response -> {
                        this.currentPartnerUniversityData = new LinkedList<>(response.getResponseData());
                        this.cursorPartnerUniversityData = 0;
                    })
            );
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetSinglePartnerUniversityAllowed(){
        return !this.currentPartnerUniversityData.isEmpty() || isLinkAvailable(PartnerUniversityRelTypes.GET_SINGLE_PARTNER_UNIVERSITY) || isLocationHeaderAvailable();
    }

    public void getSinglePartnerUniversity() throws IOException{
        if(isLocationHeaderAvailable()) {
            getSinglePartnerUniversity(getLocationHeaderURL());
        } else if (!this.currentPartnerUniversityData.isEmpty()){
            getSinglePartnerUniversity(this.cursorPartnerUniversityData);
        } else {
            throw new IllegalStateException();
        }
    }

    private void getSinglePartnerUniversity(int index) throws IOException {
        getSinglePartnerUniversity(this.currentPartnerUniversityData.get(index).getSelfLink().getUrl());
    }

    private void getSinglePartnerUniversity(String url) throws IOException {
        processResponse(this.partnerUniClient.getSinglePartnerUniversity(url), (response) -> {
            this.currentPartnerUniversityData = new LinkedList<>(response.getResponseData());
            this.cursorPartnerUniversityData = 0;
        });
    }

    public boolean isCreatePartnerUniversityAllowed() {
        return isLinkAvailable(PartnerUniversityRelTypes.CREATE_PARTNER_UNIVERSITY) || !currentPartnerUniversityData.isEmpty() || isLocationHeaderAvailable();
    }

    public void createPartnerUniversity(PartnerUniversityClientModel partnerUni) throws IOException{
        if(isLinkAvailable(PartnerUniversityRelTypes.CREATE_PARTNER_UNIVERSITY)) {
            processResponse(this.partnerUniClient.postNewPartnerUniversity(
                    getUrl(PartnerUniversityRelTypes.CREATE_PARTNER_UNIVERSITY),partnerUni),
                    (response) -> {
                this.currentPartnerUniversityData = Collections.EMPTY_LIST;
                this.cursorPartnerUniversityData = 0;
            } );
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isUpdatePartnerUniversityAllowed() {
        return isLinkAvailable(PartnerUniversityRelTypes.UPDATE_SINGLE_PARTNER_UNIVERSITY);
    }

    public void updatePartnerUniversity(PartnerUniversityClientModel partnerUni) throws IOException{
        if(isCreatePartnerUniversityAllowed()) {
            processResponse(this.partnerUniClient.putPartnerUniversity(
                            getUrl(PartnerUniversityRelTypes.UPDATE_SINGLE_PARTNER_UNIVERSITY),partnerUni),
                    (response) -> {
                        this.currentPartnerUniversityData = Collections.EMPTY_LIST;
                        this.cursorPartnerUniversityData = 0;
                    } );
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isDeletePartnerUniversityAllowed() {
        return isLinkAvailable(PartnerUniversityRelTypes.DELETE_SINGLE_PARTNER_UNIVERSITY);
    }

    public void deletePartnerUniversity() throws IOException{
        if(isDeletePartnerUniversityAllowed()) {
            processResponse(this.partnerUniClient.deletePartnerUniversity(
                            getUrl(PartnerUniversityRelTypes.DELETE_SINGLE_PARTNER_UNIVERSITY)),
                    (response) -> {
                        this.currentPartnerUniversityData = Collections.EMPTY_LIST;
                        this.cursorPartnerUniversityData = 0;
                    } );
        } else {
            throw new IllegalStateException();
        }
    }

    //Modules
    public boolean isGetAllModulesOfPartnerUniAllowed(){
        return isLinkAvailable(PartnerUniversityModuleRelTypes.GET_ALL_MODULES_OF_PARTNER_UNI);
    }

    public void getAllModulesOfPartnerUniversities() throws IOException{
        if(isGetAllModulesOfPartnerUniAllowed()) {
            processResponse(this.moduleClient.getCollectionOfModules(getUrl(PartnerUniversityModuleRelTypes.GET_ALL_MODULES_OF_PARTNER_UNI)),
                    (response -> {
                        this.currentModuleData = new LinkedList<>(response.getResponseData());
                        this.cursorModuleData = 0;
                    }));
        } else if(!this.currentPartnerUniversityData.isEmpty()) {
            processResponse(this.moduleClient.getCollectionOfModules(
                    this.currentPartnerUniversityData.get(this.cursorPartnerUniversityData).getModules().getUrl()
            ),
                    (response) -> {
                        this.currentPartnerUniversityData = Collections.EMPTY_LIST;
                        this.cursorPartnerUniversityData = 0;
                        this.currentModuleData = new LinkedList<>(response.getResponseData());
                        this.cursorModuleData = 0;
                    });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetSingleModuleOfPartnerUniAllowed() {
        return isLinkAvailable(PartnerUniversityModuleRelTypes.GET_SINGLE_MODULE_OF_PARTNER_UNI);
    }

    public void getSingleModuleOfPartnerUni() throws IOException{
        if(isLocationHeaderAvailable()) {
            getSingleModuleOfPartnerUni(getLocationHeaderURL());
        } else if (!this.currentModuleData.isEmpty()) {
            getSingleModuleOfPartnerUni(this.cursorModuleData);
        } else {
            throw new IllegalStateException();
        }
    }

    private void getSingleModuleOfPartnerUni(int index) throws IOException {
        getSingleModuleOfPartnerUni(this.currentModuleData.get(index).getSelfLink().getUrl());
    }

    private void getSingleModuleOfPartnerUni(String url) throws IOException {
        processResponse(this.moduleClient.getSingleModule(url), (response)-> {
            this.currentModuleData = new LinkedList<>(response.getResponseData());
            this.cursorModuleData = 0;
        });
    }


    public boolean isCreateModuleOfPartnerUniAllowed() {
        return isLinkAvailable(PartnerUniversityModuleRelTypes.CREATE_MODULE);
    }

    public void createModuleOfPartnerUniversity(ModuleClientModel module) throws IOException {
        if(isCreateModuleOfPartnerUniAllowed()){
            processResponse(this.moduleClient.postModule(getUrl(PartnerUniversityModuleRelTypes.CREATE_MODULE),module),
                    (response) -> {
                        this.currentModuleData = Collections.EMPTY_LIST;
                        this.cursorModuleData = 0;
                    });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isUpdateModuleOfPartnerUniversityAllowed() {
        return isLinkAvailable(PartnerUniversityModuleRelTypes.UPDATE_SINGLE_MODULE_OF_PARTNER_UNI);
    }

    public void updateModuleOfPartnerUniversity(ModuleClientModel module) throws IOException {
        if(isUpdateModuleOfPartnerUniversityAllowed()) {
            processResponse(this.moduleClient.putModule(getUrl(PartnerUniversityModuleRelTypes.UPDATE_SINGLE_MODULE_OF_PARTNER_UNI), module),
                    (response) -> {
                        this.currentModuleData = Collections.EMPTY_LIST;
                        this.cursorModuleData = 0;
                    });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isDeleteModuleOfPartnerUniAllowed(){
        return isLinkAvailable(PartnerUniversityModuleRelTypes.DELETE_SINGLE_MODULE_OF_PARTNER_UNI);
    }

    public void deleteModuleOfPartnerUniversity() throws IOException {
        if(isDeleteModuleOfPartnerUniAllowed()) {
            processResponse(this.moduleClient.deleteModule(getUrl(PartnerUniversityModuleRelTypes.DELETE_SINGLE_MODULE_OF_PARTNER_UNI)),
                    (response) -> {
                        this.currentModuleData = Collections.EMPTY_LIST;
                        this.cursorModuleData = 0;
                    });
        }
    }

}
