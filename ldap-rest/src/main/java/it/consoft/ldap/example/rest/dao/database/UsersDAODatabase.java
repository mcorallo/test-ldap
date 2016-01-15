package it.consoft.ldap.example.rest.dao.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.UsersDAO;
import it.consoft.shared.jdbc.QueryBuilder;
import it.consoft.shared.jdbc.QueryHelper;
import it.consoft.shared.jdbc.Record;
import it.consoft.shared.jdbc.TransactionBatch;

public class UsersDAODatabase extends GenericDAODatabase implements UsersDAO {

	private static final Logger logger = LoggerFactory.getLogger(UsersDAODatabase.class);

	@Override
	public User getUser(String username) throws SQLException {
		List<Record> rs = DbUtils.getQueryHelper().select(getQuery(Queries.Users.GET_BY_USERNAME), username);
		if (rs.isEmpty()) {
			return null;
		}

		return User.getInstance(rs.get(0).getCells());
	}

	@Override
	public List<User> searchUsers(String username) throws SQLException {
		List<User> result = new ArrayList<>();
		QueryBuilder qb = new QueryBuilder();
		qb.addToken(getQuery(Queries.Users.GET_ALL));
		List<Record> rs;
		if (username != null) {
			qb.addToken(getQuery(Queries.Users.LIKE_USERNAME));
			rs = DbUtils.getQueryHelper().select(qb.getQuery(), "%" + username + "%");
		} else {
			rs = DbUtils.getQueryHelper().select(qb.getQuery());
		}

		if (rs.isEmpty()) {
			return result;
		}

		for (Record r : rs) {
			result.add(User.getInstance(r.getCells()));
		}

		return result;
	}

	@Override
	public User getUser(Integer id) throws SQLException {
		QueryHelper queryHelper = DbUtils.getQueryHelper();
		List<Record> rs = queryHelper.select(getQuery(Queries.Users.GET_BY_ID), id);
		if (rs.isEmpty()) {
			return null;
		}

		return User.getInstance(rs.get(0).getCells());
	}

	@Override
	public boolean addUser(User user) throws SQLException {
		String username = user.getUsername();

		User existingUser = getUser(username);
		if (existingUser != null) {
			logger.debug("tried to add existing user {}", username);
			return false;
		}

		int inserted = DbUtils.getQueryHelper().executeUpdate(getQuery(Queries.Users.ADD), username, user.getEmail());
		return inserted == 1;
	}

	@Override
	public boolean deleteUser(final Integer id) throws SQLException {
		return DbUtils.executeTransaction(new TransactionBatch() {

			@Override
			public boolean execute(QueryHelper transactionQueryHelper) throws SQLException {

				transactionQueryHelper.executeUpdate(getQuery(Queries.UserGroups.DELETE_BY_USER), id);

				int deleted = transactionQueryHelper.executeUpdate(getQuery(Queries.Users.DELETE_BY_ID), id);
				return deleted == 1;
			}
		});
	}

	@Override
	public boolean updateUser(Integer id, User user) throws SQLException {
		int updated = DbUtils.getQueryHelper().executeUpdate(getQuery(Queries.Users.EDIT), user.getEmail(), id);
		return updated == 1;
	}

	@Override
	public void addUserGroups(final int userId, final Set<String> localGroupsAll) throws SQLException {
		DbUtils.executeTransaction(new TransactionBatch() {

			@Override
			public boolean execute(QueryHelper transactionQueryHelper) throws SQLException {

				int deleted = transactionQueryHelper.executeUpdate(getQuery(Queries.UserGroups.DELETE_BY_USER), userId);

				if (deleted == 0) {
					return false;
				}

				int inserted = 0;

				for (String lg : localGroupsAll) {
					inserted += transactionQueryHelper.executeUpdate(Queries.UserGroups.ADD_BY_USER, userId, lg);
				}
				return inserted > 0;
			}
		});
	}
}
