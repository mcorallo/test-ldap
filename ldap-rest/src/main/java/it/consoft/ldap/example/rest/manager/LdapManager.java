package it.consoft.ldap.example.rest.manager;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.DAOFactory;

public class LdapManager {

	public User getUser(String username, String password) throws Exception {
		User user = DAOFactory.getLdapDAO().getUser(username, password);

		DAOFactory.getProfilesDAO().init();
		
		String userName = "user1";
//		int inserted = queryHelper.executeUpdate("INSERT INTO test values (?)", userName);
//
//		List<Record> select = queryHelper.select("SELECT * FROM test WHERE user=?", userName);

//		List<Test> finalResult = select.stream().map(Test::getFromDb).collect(Collectors.toList());

		return user;
	}
}
