package it.consoft.ldap.example.adapter;

import it.consoft.ldap.example.adapter.fake.UsersAdapterFake;

public class AdapterFactory {

	public static UsersAdapter getUsersAdapter() {
		return new UsersAdapterFake();
	}

}
