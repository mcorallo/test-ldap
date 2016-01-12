package it.consoft.ldap.example.rest.dao.database;

import java.sql.SQLException;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.ProfilesDAO;
import it.consoft.sharedutils.jdbc.QueryBuilder;
import it.consoft.sharedutils.jdbc.QueryHelper;

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
}
