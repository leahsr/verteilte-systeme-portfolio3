package de.fhws.fiw.fds.sutton.server;

import com.github.javafaker.Faker;
import de.fhws.fiw.fds.partneruniversitymanagement.client.models.ModuleClientModel;
import de.fhws.fiw.fds.partneruniversitymanagement.client.models.PartnerUniversityClientModel;
import de.fhws.fiw.fds.partneruniversitymanagement.client.rest.PartnerUniversityManagerRestClient;
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
                addSamplePartnerUniversities(38);
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

            @Test
            public void get_single_partnerUni_allowed() {
                assertTrue(client.isGetSinglePartnerUniversityAllowed());
            }

            @Nested
            class GetNextPrevPage {
                @BeforeEach
                public void setup() throws IOException {
                    client.getNextPartnerUniversityPage();
                }

                @Test
                public void next_page_works() {
                    assertEquals(200, client.getLastStatusCode());
                }

                @Test
                public void next_page_allowed() {
                    assertFalse(client.hasNext());
                }

                @Test
                public void previous_page_allowed() {
                    assertTrue(client.hasPrevious());
                }

                @Test
                public void get_previous_page_works() throws IOException{
                    client.getPrevPartnerUniversityPage();
                    assertEquals(200, client.getLastStatusCode());
                }
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
                public void get_single_partnerUni_is_update_allowed() {
                    assertTrue(client.isUpdatePartnerUniversityAllowed());
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
                        assertEquals(newCountry, client.partnerUniversityData().getFirst().getCountry());
                        assertEquals(newNumberOfStudentsSend, client.partnerUniversityData().getFirst().getNumberOfStudentsSend());
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

                @Nested
                class Modules {
                    @Nested
                    class GetAllModulesEmpty {
                        @BeforeEach
                        public void setup() throws IOException {
                            client.getAllModulesOfPartnerUniversities();
                        }

                        @Test
                        public void get_all_modules_works() {
                            assertEquals(200, client.getLastStatusCode());
                        }

                        @Test
                        public void get_single_module_not_available() {
                            assertFalse(client.isGetSingleModuleOfPartnerUniAllowed());
                        }

                        @Test
                        void create_module_allowed() {
                            assertTrue(client.isCreateModuleOfPartnerUniAllowed());
                        }

                        @Test
                        void is_get_all_modules_collection_empty() {
                            assertTrue(client.moduleData().isEmpty());
                        }

                        @Nested
                        class CreateModule {
                            public ModuleClientModel sampleModule = createSampleModule();

                            @BeforeEach
                            public void setup() throws IOException {
                                client.createModuleOfPartnerUniversity(sampleModule);
                            }

                            @Test
                            public void create_module_works() {
                                assertEquals(201, client.getLastStatusCode());
                            }

                            @Test
                            public void is_get_single_module_allowed() {
                                assertTrue(client.isGetSingleModuleOfPartnerUniAllowed());
                            }

                            @Nested
                            class getSingleModule {
                                @BeforeEach
                                public void setup() throws IOException {
                                    client.getSingleModuleOfPartnerUni();
                                }

                                @Test
                                public void get_single_module_works() {
                                    assertEquals(200, client.getLastStatusCode());
                                }

                                @Test
                                public void is_single_module_correct() {
                                    assertEquals(sampleModule.getName(), client.moduleData().getFirst().getName());
                                    assertEquals(sampleModule.getSemester(), client.moduleData().getFirst().getSemester());
                                    assertEquals(sampleModule.getEcts(), client.moduleData().getFirst().getEcts());
                                }

                                @Test
                                public void is_get_all_modules_allowed() {
                                    assertTrue(client.isGetAllModulesOfPartnerUniAllowed());
                                }

                                @Test
                                public void is_delete_module_available() {
                                    assertTrue(client.isDeleteModuleOfPartnerUniAllowed());
                                }

                                @Nested
                                class UpdateModule {
                                    String updatedModuleName = "New Module Name";
                                    @BeforeEach
                                    public void setup() throws IOException{
                                        var module = client.moduleData().getFirst();
                                        module.setName(updatedModuleName);
                                        client.updateModuleOfPartnerUniversity(module);
                                    }

                                    @Test
                                    public void update_module_works() {
                                        assertEquals(204, client.getLastStatusCode());
                                    }

                                    @Test
                                    public void is_update_module_correct() throws IOException {
                                        client.getSingleModuleOfPartnerUni();
                                        assertEquals(updatedModuleName, client.moduleData().getFirst().getName());
                                    }
                                }

                                @Nested
                                class DeleteModule {
                                    @BeforeEach
                                    public void setup() throws IOException {
                                        client.deleteModuleOfPartnerUniversity();
                                    }

                                    @Test
                                    public void delete_module_works() {
                                        assertEquals(204, client.getLastStatusCode());
                                    }

                                    @Test
                                    public void is_get_all_modules_allowed() {
                                        assertTrue(client.isGetAllModulesOfPartnerUniAllowed());
                                    }
                                }
                            }
                        }

                        @Nested
                        class CreateInvalidModule {
                            public ModuleClientModel sampleModule = createSampleModule();

                            @BeforeEach
                            public void setup() throws IOException{
                                sampleModule.setEcts(-5);
                                client.createModuleOfPartnerUniversity(sampleModule);
                            }

                            @Test
                            public void invalid_input() {
                                assertEquals(400, client.getLastStatusCode());
                            }
                        }
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
                    assertTrue(client.isGetSinglePartnerUniversityAllowed());
                }

                @Test
                public void create_partnerUni_valid() throws IOException{
                    client.getSinglePartnerUniversity();
                    assertEquals(client.partnerUniversityData().getFirst().getName(), newUni.getName());
                    assertEquals(client.partnerUniversityData().getFirst().getCountry(), newUni.getCountry());
                    assertEquals(client.partnerUniversityData().getFirst().getNumberOfStudentsSend(), newUni.getNumberOfStudentsSend());
                }
            }

            @Nested
            class CreateInvalidPartnerUniversity {
                private PartnerUniversityClientModel newUni = createSamplePartnerUniversity();
                @BeforeEach
                public void setup() throws IOException{
                    newUni.setNumberOfStudentsSend(-23);
                    client.createPartnerUniversity(newUni);
                }

                @Test
                public void invalid_input() {
                    assertEquals(400, client.getLastStatusCode());
                }
            }
        }

        @Nested
        class Ordering {
            String firstName = "aaaaa";
            String secondName = "zzzzz";

            @BeforeEach
            public void setup() throws IOException {
                var addDataClient = new PartnerUniversityManagerRestClient();
                var addDataClient2 = new PartnerUniversityManagerRestClient();
                addDataClient.start();
                addDataClient2.start();
                addDataClient.getAllPartnerUniversities();
                addDataClient2.getAllPartnerUniversities();

                PartnerUniversityClientModel firstUni = createSamplePartnerUniversity();
                firstUni.setName(firstName);
                PartnerUniversityClientModel secondUni = createSamplePartnerUniversity();
                secondUni.setName(secondName);

                addDataClient.createPartnerUniversity(firstUni);
                addDataClient2.createPartnerUniversity(secondUni);

                client.getAllPartnerUniversities();
            }

            @Test
            public void get_all_works() {
                assertEquals(200, client.getLastStatusCode());
            }

            @Nested
            class Descending {
                @BeforeEach
                public void setup() throws IOException {
                    client.getAllPartnerUniversitiesByNameAndCountryDesc("", "");
                }

                @Test
                public void is_order_correct() {
                    assertEquals(secondName, client.partnerUniversityData().get(1).getName());
                    assertEquals(firstName, client.partnerUniversityData().get(0).getName());
                }
            }
            @Nested
            class Ascending {
                @BeforeEach
                public void setup() throws IOException {
                    client.getAllPartnerUniversitiesByNameAndCountryAsc("","");
                }

                @Test
                public void is_order_correct() {
                    assertEquals(firstName, client.partnerUniversityData().get(0).getName());
                    assertEquals(secondName, client.partnerUniversityData().get(1).getName());
                }
            }

            @Nested
            class GetByNameAndCountry {
                @BeforeEach
                public void setup() throws IOException{
                    client.getAllPartnerUniversitiesByNameAndCountry("aaaaa", "");
                }

                @Test
                public void get_by_name_works() {
                    assertEquals(200, client.getLastStatusCode());
                }

                @Test
                public void get_by_name_is_correct() {
                    assertEquals(firstName, client.partnerUniversityData().getFirst().getName());
                    assertEquals(1, client.partnerUniversityData().size());
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
            String title = faker.company().buzzword();
            ModuleClientModel module = new ModuleClientModel();
            module.setName(title);
            module.setEcts(5);
            module.setSemester(1);
            return module;
        }
    }
