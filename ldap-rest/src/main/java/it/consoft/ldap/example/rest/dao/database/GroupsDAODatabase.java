package it.consoft.ldap.example.rest.dao.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.consoft.ldap.example.rest.bean.Group;
import it.consoft.ldap.example.rest.dao.GroupsDAO;
import it.consoft.ldap.example.rest.dao.database.Queries.GroupsRel;
import it.consoft.shared.jdbc.QueryBuilder;
import it.consoft.shared.jdbc.QueryHelper;
import it.consoft.shared.jdbc.Record;
import it.consoft.shared.jdbc.TransactionBatch;

public class GroupsDAODatabase extends GenericDAODatabase implements GroupsDAO {

	@Override
	public List<String> getLocalGroups(String externalGroup) throws SQLException {
		QueryHelper queryHelper = DbUtils.getQueryHelper();
		List<Record> rs = queryHelper.select(getQuery(Queries.GroupsRel.GET_BY_LDAP), externalGroup);
		List<String> result = new ArrayList<>();
		for (Record r : rs) {
			result.add((String) r.getValue("LOCAL_GROUP_NAME"));
		}
		return result;
	}

	@Override
	public List<Group> searchGroups(String ldapGroupName, String localGroupName) throws SQLException {
		QueryHelper queryHelper = DbUtils.getQueryHelper();

		QueryBuilder qb = new QueryBuilder();
		qb.addToken(Queries.GroupsRel.SEARCH);

		if (ldapGroupName != null) {
			qb.addToken(Queries.GroupsRel.BY_LDAP);
		}

		if (localGroupName != null) {
			qb.addToken(Queries.GroupsRel.BY_LOCAL);
		}

		List<Record> rs = queryHelper.select(getQuery(Queries.GroupsRel.SEARCH), ldapGroupName, localGroupName);
		List<Group> result = new ArrayList<>();
		for (Record r : rs) {
			result.add(Group.getInstance(r.getCells()));
		}
		return result;
	}

	@Override
	public Boolean deleteGroups(final String ldapGroupName) throws SQLException {
		return DbUtils.executeTransaction(new TransactionBatch() {

			@Override
			public boolean execute(QueryHelper transactionQueryHelper) throws SQLException {

				List<Record> rs = transactionQueryHelper.select(Queries.GroupsRel.GET_BY_LDAP, ldapGroupName);

				for (Record r : rs) {
					String localGroupName = r.getValue("LOCAL_GROUP_NAME");

					List<Record> rs2 = transactionQueryHelper.select(GroupsRel.COUNT_BY_LOCAL, localGroupName);
					Record r2 = rs2.get(0);
					int remainingLocalReferences = r2.getValue("TOT");
					if (remainingLocalReferences == 0) {
						transactionQueryHelper.executeUpdate(getQuery(Queries.UserGroups.DELETE_BY_LOCAL), localGroupName);
					}
				}

				int deleted = transactionQueryHelper.executeUpdate(getQuery(Queries.GroupsRel.DELETE_BY_LDAP), ldapGroupName);

				if (deleted == 0) {
					return false;
				}

				return true;
			}
		});
	}

	@Override
	public Boolean deleteGroup(final String ldapGroupName, final String localGroupName) throws SQLException {
		return DbUtils.executeTransaction(new TransactionBatch() {

			@Override
			public boolean execute(QueryHelper transactionQueryHelper) throws SQLException {

				int deleted = transactionQueryHelper.executeUpdate(getQuery(Queries.GroupsRel.DELETE), ldapGroupName, localGroupName);
				if (deleted == 0) {
					return false;
				}

				List<Record> rs = transactionQueryHelper.select(GroupsRel.COUNT_BY_LOCAL, localGroupName);
				Record r = rs.get(0);
				int remainingLocalReferences = r.getValue("TOT");
				if (remainingLocalReferences == 0) {
					transactionQueryHelper.executeUpdate(getQuery(Queries.UserGroups.DELETE_BY_LOCAL), localGroupName);
				}
				return true;
			}
		});
	}

	@Override
	public List<String> getUserGroups(Integer id) throws SQLException {
		List<Record> rs = DbUtils.getQueryHelper().select(getQuery(Queries.UserGroups.GET_BY_USER), id);
		List<String> result = new ArrayList<>();
		for (Record r : rs) {
			result.add((String) r.getValue("LOCAL_GROUP_NAME"));
		}
		return result;
	}

	@Override
	public Boolean addGroup(String ldapGroupName, String localGroupName) throws SQLException {
		int inserted = DbUtils.getQueryHelper().executeUpdate(getQuery(Queries.GroupsRel.ADD), ldapGroupName, localGroupName);
		return inserted > 0;
	}
}
