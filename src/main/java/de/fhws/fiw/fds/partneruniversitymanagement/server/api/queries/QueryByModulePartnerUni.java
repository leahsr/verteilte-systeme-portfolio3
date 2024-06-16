package de.fhws.fiw.fds.partneruniversitymanagement.server.api.queries;

import de.fhws.fiw.fds.partneruniversitymanagement.server.api.models.Module;
import de.fhws.fiw.fds.partneruniversitymanagement.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class QueryByModulePartnerUni<R> extends AbstractRelationQuery<R, Module> {

    public QueryByModulePartnerUni(long primaryId, int offset, int size) {
        super(primaryId);
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<>(offset, size);
    }

    @Override
    protected CollectionModelResult<Module> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getPartnerUniversityModuleDao().readAllLinked(primaryId);
    }
}
