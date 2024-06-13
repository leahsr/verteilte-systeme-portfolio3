package de.fhws.fiw.fds.sutton.server;

import com.github.javafaker.Faker;
import de.fhws.fiw.fds.partnerUniversityManagement.client.models.ModuleClientModel;
import de.fhws.fiw.fds.partnerUniversityManagement.client.models.PartnerUniversityClientModel;
import de.fhws.fiw.fds.partnerUniversityManagement.client.rest.PartnerUniversityManagerRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
        public void dispatcher_works() throws IOException {
            assertEquals(200, client.getLastStatusCode());
        }

        @Test
        public void dispatcher_is_get_all_partnerUniversities_allowed() throws IOException {
            assertTrue(client.isGetAllPartnerUniversitiesAllowed());
        }

        @Nested
        class GetAllPartnerUniversities {

            @BeforeEach
            public void setup() throws IOException {
                addSamplePartnerUniversities(20);
                client.getAllPartnerUniversities();
            }

            @Test
            public void get_all_partnerUni_works() {
                assertEquals(200, client.getLastStatusCode());
            }

            @Test
            public void get_all_partnerUni_by_name_and_country_allowed() throws IOException {
                assertTrue(client.isGetAllPartnerUniversitiesByNameAndCountryAllowed());
            }

            @Test
            public void get_all_partnerUni_by_name_and_country_desc_allowed() throws IOException {
                assertTrue(client.isGetAllPartnerUniversitiesByNameAndCountryDescAllowed());
            }

            @Test
            public void get_all_partnerUni_by_name_and_country_asc_allowed() throws IOException {
                assertTrue(client.isGetAllPartnerUniversitiesByNameAndCountryAscAllowed());
            }

            @Test
            public void get_all_partnerUni_is_getSingle_partnerUni_allowed() {
                assertTrue(client.isGetSinglePartnerUniversityAllowed());
            }

            @Test
            public void get_all_partnerUni_is_create_partnerUni_allowed() throws IOException {
                assertTrue(client.isCreatePartnerUniversityAllowed());
            }

            @Test
            public void get_all_partnerUni_is_next_page_allowed() {
                assertTrue(client.hasNext());
            }

            @Test
            public void get_all_partnerUni_is_previous_not_allowed() {
                assertFalse(client.hasPrevious());
            }

            @Nested
            class GetSinglePartnerUniversity {

                @BeforeEach
                public void setup() throws IOException {
                    client.setPartnerUniversityCursor(0);
                    client.getSinglePartnerUniversity();
                }

                @Test
                public void get_single_partnerUni_works() {
                    assertEquals(200, client.getLastStatusCode());
                }

                @Test
                public void get_single_partnerUni_is_getAll_partnerUnis_allowed() {
                    assertTrue(client.isGetAllPartnerUniversitiesAllowed());
                }

                @Test
                public void get_single_partnerUni_is_create_partnerUni_allowed() {
                    assertTrue(client.isCreatePartnerUniversityAllowed());
                }

                @Test
                public void get_single_partnerUni_is_delete_partnerUni_allowed() {
                    assertTrue(client.isDeletePartnerUniversityAllowed());
                }

                @Nested
                class UpdatePartnerUniversity {
                    String newName = "Newname";
                    String newCountry = "TakatukaLand";
                    int newNumberOfStudentsSend = 33;

                    @BeforeEach
                    public void setup() throws IOException {
                        var unis = client.partnerUniversityData().getFirst();
                        unis.setName(newName);
                        unis.setCountry(newCountry);
                        unis.setNumberOfStudentsSend(newNumberOfStudentsSend);
                        client.updatePartnerUniversity(unis);
                    }

                    @Test
                    public void update_partnerUni_works() {
                        assertEquals(204, client.getLastStatusCode());
                    }

                    @Test
                    public void is_get_single_partnerUni_allowed() {
                        assertTrue(client.isGetSinglePartnerUniversityAllowed());
                    }

                    @Test
                    public void is_value_updated() throws IOException {
                        client.getSinglePartnerUniversity();
                        assertEquals(newName, client.partnerUniversityData().getFirst().getName());
                    }
                }

                @Nested
                class DeletePartnerUniversity {
                    @BeforeEach
                    public void setup() throws IOException {
                        client.deletePartnerUniversity();
                    }

                    @Test
                    public void delete_partnerUni_works() {
                        assertEquals(204, client.getLastStatusCode());
                    }

                    @Test
                    public void is_get_all_partnerUni_allowed() throws IOException {
                        assertTrue(client.isGetAllPartnerUniversitiesAllowed());
                    }
                }
            }

            @Nested
            class CreatePartnerUniversity {
                private PartnerUniversityClientModel newUni = createSamplePartnerUniversity();
                @BeforeEach
                public void setup() throws IOException{
                    client.createPartnerUniversity(newUni);
                }

                @Test
                public void create_partnerUni_works() {
                    assertEquals(201, client.getLastStatusCode());
                }

                @Test
                public void is_get_all_partnerUni_allowed() throws IOException {
                    assertTrue(client.isGetAllPartnerUniversitiesAllowed());
                }

                @Test
                public void create_partnerUni_valid() throws IOException{
                    client.getSinglePartnerUniversity();
                    assertEquals(client.partnerUniversityData().getFirst().getName(), newUni.getName());
                    assertEquals(client.partnerUniversityData().getFirst().getCountry(), newUni.getCountry());
                    assertEquals(client.partnerUniversityData().getFirst().getNumberOfStudentsSend(), newUni.getNumberOfStudentsSend());
                }
            }
        }
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

        public void addSamplePartnerUniversities(int numberOfSamples) throws IOException {
            var addDataClient = new PartnerUniversityManagerRestClient();
            for (int i = 0; i <= numberOfSamples; i++) {
                addDataClient.start();
                addDataClient.getAllPartnerUniversities();
                addDataClient.createPartnerUniversity(createSamplePartnerUniversity());
            }
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
