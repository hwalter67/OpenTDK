package RegressionTest.JSONContainer;

import org.opentdk.api.datastorage.DataContainer;

import RegressionTest.BaseRegression;

public class RT_Container_readJson extends BaseRegression {

	public static void main(String[] args) {
		new RT_Container_readJson();
	}

	@Override
	protected void runTest() {
		DataContainer dc = new DataContainer("testdata/RegressionTestData/JsonExample.json");
		// Only possible at top level
		BaseRegression.testResult(dc.getValue("id"), "ID", "1");
		BaseRegression.testResult(dc.getValue("name"), "NAME", "LK");
		BaseRegression.testResult(dc.getValue("permanent"), "PERM", "true");
		BaseRegression.testResult(dc.getValue("address"), "ADDRESS", "{\"zipcode\":87463,\"city\":\"Munich\",\"street\":\"Schneckenburgerstrasse\"}");
		BaseRegression.testResult(dc.getValue("phoneNumbers"), "PHONE_NUMBERS", "[123456,987654]");
		BaseRegression.testResult(dc.getValue("cities"), "CITIES", "[\"Munich\",\"Berlin\"]");
//		BaseRegression.testResult(dc.getValue("properties;age"), "AGE", "1");
			
		BaseRegression.testResult(dc.getAttributes("properties", "salary")[0], "SALARY", "1000 EUR");
		BaseRegression.testResult(dc.getAttributes("properties/titles", "Sir")[0], "Sir", "true");
		BaseRegression.testResult(dc.getAttributes("properties;titles", "Sir")[0], "Sir", "true");
		
	}

}
