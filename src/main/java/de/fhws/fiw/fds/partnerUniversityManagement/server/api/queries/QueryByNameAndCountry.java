package de.fhws.fiw.fds.partnerUniversityManagement.server.api.queries;

import de.fhws.fiw.fds.partnerUniversityManagement.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.partnerUniversityManagement.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class QueryByNameAndCountry<R> extends AbstractQuery<R, PartnerUniversity> {

    private String name;
    private String country;

    public QueryByNameAndCountry(String name, String country, int offset, int size) {
        this.name = name;
        this.country = country;
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<>(offset, size);
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

    @Override
    protected CollectionModelResult<PartnerUniversity> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException throws DatabaseException {
        return DaoFactory.getInstance().getPartnerUniversityDao().readByNameAndCountry(this.name, this.country, searchParameter);
    }
}
