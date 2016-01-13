package it.consoft.ldap.example.rest.dao.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.ProfilesDAO;
import it.consoft.shared.jdbc.QueryBuilder;
import it.consoft.shared.jdbc.QueryHelper;
import it.consoft.shared.jdbc.Record;

public class ProfilesDAODatabase implements ProfilesDAO {

	@Override
	public Object getUserProfile(Integer userId) {
		throw new RuntimeException("JDBC layer not implemented.");
	}

	@Override
	public void init() throws SQLException {
		QueryHelper queryHelper = DbUtils.getQueryHelper();

		QueryBuilder builder = new QueryBuilder();
		builder.addToken("CREATE TABLE IF NOT EXISTS users");
		builder.addToken("(id int primary key auto_increment not null,  username varchar(50) not null)");
		queryHelper.executeUpdate(builder.getQuery());

		builder.clear();
		builder.addToken("CREATE TABLE IF NOT EXISTS profiles");
		builder.addToken("(id int primary key auto_increment not null,  name varchar(50) not null)");
		queryHelper.executeUpdate(builder.getQuery());

		builder.clear();
		builder.addToken("CREATE TABLE IF NOT EXISTS user_profiles");
		builder.addToken("(");
		builder.addToken("user_id int not null references users(id), profile_id int not null references profiles(id)");
		builder.addToken(", primary key (user_id, profile_id)");
		builder.addToken(")");
		queryHelper.executeUpdate(builder.getQuery());

		builder.clear();
		builder.addToken("CREATE TABLE IF NOT EXISTS ldap_local_group_rel");
		builder.addToken("(");
		builder.addToken("ldap_group_name varchar(255) not null, local_group_name varchar(255) not null");
		builder.addToken(", primary key (ldap_group_name, local_group_name)");
		builder.addToken(")");
		queryHelper.executeUpdate(builder.getQuery());

		builder.clear();
		builder.addToken("select count(*) tot from ldap_local_group_rel where ldap_group_name = 'Nikola Tesla'");
		List<Record> rs = queryHelper.select(builder.getQuery());
		Long count = rs.get(0).getValue("TOT");
		if (count == 0) {
			builder.clear();
			builder.addToken("insert into ldap_local_group_rel values('Nikola Tesla','admin')");
			queryHelper.executeUpdate(builder.getQuery());
		}
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
