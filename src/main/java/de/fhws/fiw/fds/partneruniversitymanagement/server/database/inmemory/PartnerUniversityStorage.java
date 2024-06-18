package de.fhws.fiw.fds.partneruniversitymanagement.server.database.inmemory;

import de.fhws.fiw.fds.partneruniversitymanagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.partneruniversitymanagement.server.database.PartnerUniversityDao;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PartnerUniversityStorage extends AbstractInMemoryStorage<PartnerUniversity> implements PartnerUniversityDao {

    @Override
    public CollectionModelResult<PartnerUniversity> readByNameAndCountry(String name, String country, SearchParameter searchParameter) {
        final CollectionModelResult<PartnerUniversity> filteredResult = new CollectionModelResult<>(filterBy(byNameAndCountry(name, country)));
        final CollectionModelResult<PartnerUniversity> orderedResult = new CollectionModelResult<>(
                orderBy(filteredResult.getResult(),
                        (a,b) -> {
                            if (searchParameter.getOrderByAttribute().equalsIgnoreCase("asc"))
                                return a.getName().compareToIgnoreCase(b.getName());
                            else if (searchParameter.getOrderByAttribute().equalsIgnoreCase("desc"))
                                return b.getName().compareToIgnoreCase(a.getName());
                            else
                                return 0;
                })
        );
        final CollectionModelResult<PartnerUniversity> page = InMemoryPaging.page(orderedResult, searchParameter.getOffset(), searchParameter.getSize());
        final CollectionModelResult<PartnerUniversity> returnValue =
                new CollectionModelResult<>(clone(page.getResult()));
        returnValue.setTotalNumberOfResult(filteredResult.getTotalNumberOfResult());
        return returnValue;
    }

    private Collection<PartnerUniversity> filterBy(final Predicate<PartnerUniversity> predicate) {
        return this.storage.values().stream().filter(predicate).collect(Collectors.toList());
    }

    private Collection<PartnerUniversity> orderBy(final Collection<PartnerUniversity> result, final Comparator<PartnerUniversity> comparator) {
        return result.stream().map(this::clone).collect(Collectors.toList());

    }

    @Override
    public void resetDatabase() {
        this.storage.clear();
    }

    private Predicate<PartnerUniversity> byNameAndCountry(String name, String country) {
        return p -> (name.isEmpty() || p.getName().contains(name)) && (country.isEmpty() || p.getCountry().contains(country));
    }
}
