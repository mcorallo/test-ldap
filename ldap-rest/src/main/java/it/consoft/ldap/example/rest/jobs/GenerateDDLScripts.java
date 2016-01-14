package it.consoft.ldap.example.rest.jobs;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

import it.consoft.ldap.example.rest.util.RestUtils;
import it.consoft.shared.common.configuration.ConfigurationManager;

public class GenerateDDLScripts {

	public static void main(String[] args) throws IOException {
		String environment = args[0];
		String ddlOutputFolder = args[1];
		System.out.println("generating ddl script in " + ddlOutputFolder + " ...");

		ConfigurationManager queriesManager = RestUtils.getDdlConfigurationManager();
		queriesManager.setEnv(environment);

		File ddlFolder = new File(ddlOutputFolder);
		if (!ddlFolder.exists()) {
			ddlFolder.mkdirs();
		}
		File ddlScript = new File(ddlOutputFolder, "ddl.sql");
		ddlScript.createNewFile();

		Collection<String> orderedDllQueries = getOrderedDllQueries(queriesManager);

		for (String q : orderedDllQueries) {
			FileUtils.write(ddlScript, q + ";\n", true);
		}
		System.out.println("ddl script generated and written correctly");
	}

	public static Collection<String> getOrderedDllQueries(ConfigurationManager queriesManager) {
		Map<String, String> subset = queriesManager.getAllEnvironmentProperties();
		//create a treemap to order the subset
		TreeMap<String, String> treeMap = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				Integer i1 = Integer.parseInt(o1.substring(0, o1.indexOf('.')));
				Integer i2 = Integer.parseInt(o2.substring(0, o2.indexOf('.')));
				int compareTo = i1.compareTo(i2);
				return compareTo != 0 ? compareTo : 1;
			}
		});
		treeMap.putAll(subset);
		return treeMap.values();
	}

}
