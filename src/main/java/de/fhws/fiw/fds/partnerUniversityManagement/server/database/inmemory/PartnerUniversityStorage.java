package de.fhws.fiw.fds.partnerUniversityManagement.server.database.inmemory;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.PartnerUniversityDao;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

import java.util.function.Predicate;

public class PartnerUniversityStorage extends AbstractInMemoryStorage<PartnerUniversity> implements PartnerUniversityDao {

    @Override
    public CollectionModelResult<PartnerUniversity> readByNameAndCountry(String name, String country, SearchParameter searchParameter) {
        return InMemoryPaging.page(this.readAllByPredicate(byNameAndCountry(name, country), searchParameter), searchParameter.getOffset(), searchParameter.getSize());
    }

    @Override
    public void resetDatabase() {
    }

    private Predicate<PartnerUniversity> byNameAndCountry(String name, String country) {
        return p -> (name.isEmpty() || p.getName().equals(name)) && (country.isEmpty() || p.getCountry().equals(country));
    }
}
