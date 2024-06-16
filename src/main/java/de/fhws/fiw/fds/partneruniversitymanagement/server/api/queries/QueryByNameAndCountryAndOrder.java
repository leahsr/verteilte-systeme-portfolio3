package de.fhws.fiw.fds.partneruniversitymanagement.server.api.queries;

import de.fhws.fiw.fds.partneruniversitymanagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.partneruniversitymanagement.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

import java.util.Collection;

public class QueryByNameAndCountryAndOrder<R> extends AbstractQuery<R,
        PartnerUniversity> {

    private String name;
    private String country;
    private String order;

    public QueryByNameAndCountryAndOrder(String name, String country, String order, int offset, int size) {
        this.name = name;
        this.country = country;
        this.order = order;
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<>(offset, size);
    }

    @Override
    protected CollectionModelResult<PartnerUniversity> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        CollectionModelResult<PartnerUniversity> unis = DaoFactory.getInstance().getPartnerUniversityDao().readByNameAndCountry(this.name, this.country, searchParameter);
        Collection<PartnerUniversity> uniCollection = unis.getResult();

        var sortedUnis = unis.getResult().stream().sorted((a,b) -> {
            if(this.order.equalsIgnoreCase("asc")) {
                return a.getName().compareToIgnoreCase(b.getName());
            }
            else if(this.order.equalsIgnoreCase("desc")) {
                return b.getName().compareToIgnoreCase(a.getName());
            }
            else return 0;
        }).toList();

        uniCollection.clear();
        uniCollection.addAll(sortedUnis);
        return unis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

}
