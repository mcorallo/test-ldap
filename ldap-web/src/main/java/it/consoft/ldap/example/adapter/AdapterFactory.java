package it.consoft.ldap.example.adapter;

import it.consoft.ldap.example.adapter.rest.UsersAdapterRest;

public class AdapterFactory {

	public static UsersAdapter getUsersAdapter() {
		return new UsersAdapterRest();
	}

}
