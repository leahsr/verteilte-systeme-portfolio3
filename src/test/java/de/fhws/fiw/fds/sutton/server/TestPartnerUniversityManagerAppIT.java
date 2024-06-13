package de.fhws.fiw.fds.sutton.server;

import com.github.javafaker.Faker;
import de.fhws.fiw.fds.partnerUniversityManagement.client.models.ModuleClientModel;
import de.fhws.fiw.fds.partnerUniversityManagement.client.models.PartnerUniversityClientModel;
import de.fhws.fiw.fds.partnerUniversityManagement.client.rest.PartnerUniversityManagerRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPartnerUniversityManagerAppIT {
    final private Faker faker = new Faker();
    private PartnerUniversityManagerRestClient client;

    @BeforeEach
    public void setUp() throws IOException {
        this.client = new PartnerUniversityManagerRestClient();
        this.client.resetDatabase();
    }

    @Nested
    class Dispatcher {

        @BeforeEach
        public void setup() throws IOException {
            client.start();
        }

        @Test
        public void dispatcher_is_available() throws IOException {
            client.start();
            assertEquals(200, client.getLastStatusCode());
        }

        @Test
        public void dispatcher_is_get_all_partnerUniversities_allowed() throws IOException {
            client.start();
            assertTrue(client.isGetAllPartnerUniversitiesAllowed());
        }
    }

    @Nested
    class GetAllPartnerUniversities {

        @BeforeEach
        public void setup() throws IOException {
            client.start();
        }

        @Test
        public void
    }

    // Helper
    public PartnerUniversityClientModel createSamplePartnerUniversity() {
        var university = faker.university().name();
        var country = faker.country().name();
        var contact = faker.name().name();
        var url = faker.internet().url();

        PartnerUniversityClientModel partnerUni = new PartnerUniversityClientModel();
        partnerUni.setName(university);
        partnerUni.setCountry(country);
        partnerUni.setWebsite(url);
        partnerUni.setContactPerson(contact);
        partnerUni.setDepartment("Department of Computer Science");
        partnerUni.setNumberOfStudentsSend(10);
        partnerUni.setNumberOfStudentsReceive(20);

        return partnerUni;
    }

    public ModuleClientModel createSampleModule() {
        var title = faker.company().buzzword();
        ModuleClientModel module = new ModuleClientModel();
        module.setName(title);
        module.setEcts(5);
        module.setSemester(1);
        return module;
    }

    
}
