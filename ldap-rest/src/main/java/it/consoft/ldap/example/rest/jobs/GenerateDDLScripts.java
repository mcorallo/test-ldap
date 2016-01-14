package it.consoft.ldap.example.rest.jobs;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import it.consoft.ldap.example.rest.util.RestUtils;
import it.consoft.shared.common.configuration.ConfigurationManager;

public class GenerateDDLScripts {

	public static void main(String[] args) throws IOException {
		String environment = args[0];
		String ddlOutputFolder = args[1];
		System.out.println("generating ddl script in " + ddlOutputFolder + " ...");

		ConfigurationManager queriesManager = RestUtils.getQueries();
		queriesManager.setEnv(environment);

		File ddlFolder = new File(ddlOutputFolder);
		if (!ddlFolder.exists()) {
			ddlFolder.mkdirs();
		}
		File ddlScript = new File(ddlOutputFolder, "ddl.sql");
		ddlScript.createNewFile();

		Map<String, String> subset = queriesManager.getSubset("ddl");
//		System.out.println("generating ddl script for subset: " + subset);
		for (String q : subset.values()) {
			FileUtils.write(ddlScript, q + ";\n", true);
		}
		System.out.println("ddl script generated and written correctly");
	}

}
