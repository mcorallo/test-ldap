package it.consoft.ldap.example.rest.dao.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.ProfilesDAO;
import it.consoft.shared.jdbc.QueryBuilder;
import it.consoft.shared.jdbc.QueryHelper;
import it.consoft.shared.jdbc.Record;

public class ProfilesDAODatabase implements ProfilesDAO {
	private static final Logger logger = LoggerFactory.getLogger(ProfilesDAODatabase.class);

	@Override
	public Object getUserProfile(Integer userId) {
		throw new RuntimeException("JDBC layer not implemented.");
	}

	@Override
	public void init() throws SQLException {

	}

	@Override
	public int createLocalUser(User user) throws SQLException {
		QueryHelper queryHelper = DbUtils.getQueryHelper();

		QueryBuilder builder = new QueryBuilder();
		builder.addToken("INSERT INTO users (username) values(?)");
		int inserted = queryHelper.executeUpdate(builder.getQuery(), user.getUsername());
		return inserted;
	}

	@Override
	public String getLocalGroup(String externalGroup) {
		throw new RuntimeException("JDBC layer not implemented.");
	}

	@Override
	public List<String> getLocalGroups(String externalGroup) throws SQLException {
		QueryHelper queryHelper = DbUtils.getQueryHelper();
		queryHelper.setLogQueries(true);
		QueryBuilder builder = new QueryBuilder();
		builder.addToken("select local_group_name from ldap_local_group_rel where ldap_group_name = ?");
		List<Record> rs = queryHelper.select(builder.getQuery(), externalGroup);
		List<String> result = new ArrayList<>();
		for (Record r : rs) {
			result.add((String) r.getValue("LOCAL_GROUP_NAME"));
		}
		return result;
	}
}
