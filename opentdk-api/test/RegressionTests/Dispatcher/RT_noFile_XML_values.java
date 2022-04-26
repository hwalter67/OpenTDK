package RegressionTests.Dispatcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.opentdk.api.application.EBaseSettings;
import org.opentdk.api.util.ListUtil;

public class RT_noFile_XML_values {

	@BeforeClass
	public static void init() throws IOException {

	}

	private void testSettingsField(String actual, String fieldName, String expected) {
		if(actual.contentEquals(expected)) {
			System.out.println("Success: " + fieldName + " == " + actual);
		} else {
			System.out.println("Failure: " + fieldName + " is \"" + actual + "\" but should be \"" + expected + "\"");
		}
	}

	@Test
	public void test() {		
		
		// Gets the default values of dispatcher components that are declared within EBaseSettings class
		testSettingsField(E_XMLFile_Dispatcher_values.APP_LOGFILE.getValue(), "APP_LOGFILE", "./logs/Application.log");
		testSettingsField(E_XMLFile_Dispatcher_values.APP_LOGFILE_LIMIT.getValue(), "APP_LOGFILE_LIMIT", "4000");
		testSettingsField(E_XMLFile_Dispatcher_values.APP_TRACE_LEVEL.getValue(), "APP_TRACE_LEVEL", "1");
		
		// Adds Items to the associated XML Container
		E_XMLFile_Dispatcher_values.PROJECT_LOCATION.addValue("Project 1", true);
		E_XMLFile_Dispatcher_values.PROJECT_LOCATION.addValue("Project 2", true);
		E_XMLFile_Dispatcher_values.PROJECT_LOCATION.addValue("Project 3", true);
		
		// Gets the first value
		testSettingsField(E_XMLFile_Dispatcher_values.PROJECT_LOCATION.getValue(), "PROJECT_LOCATION", "Project 1");
		
		// Replaces existing value needs to be implemented!
		E_XMLFile_Dispatcher_values.PROJECT_LOCATION.setValue("", "Project 2", "Project 2.1");
		List<String> lstPL = Arrays.asList(E_XMLFile_Dispatcher_values.PROJECT_LOCATION.getValues());
		if(lstPL.contains("Project 2.1")) {
			System.out.println("Success: PROJECT_LOCATION == Project 2.1");
		} else {
			System.out.println("Failure: PROJECT_LOCATION Project 2.1 not found");
		}
		
		// Sets all values under PROJECT_LOCATIONS to "Project 4"
		E_XMLFile_Dispatcher_values.PROJECT_LOCATION.setValue("Project 4");
		testSettingsField(E_XMLFile_Dispatcher_values.PROJECT_LOCATION.getValue(), "PROJECT_LOCATION", "Project 4");
		
		// Size stays the same
		testSettingsField(String.valueOf(E_XMLFile_Dispatcher_values.PROJECT_LOCATION.getValues().length), "PROJECT_LOCATIONS.length", "3");
		
		// Delete whole setting
		E_XMLFile_Dispatcher_values.PROJECT_LOCATIONS.delete();
		testSettingsField(ListUtil.asString(E_XMLFile_Dispatcher_values.PROJECT_LOCATIONS.getValues(), ";"), "PROJECT_LOCATIONS.delete", "");

		// Add value for tags with attribute
		E_XMLFile_Dispatcher_values.BACKGROUND_IMAGE.addValue("Dark Theme", "Sunset.jpg", false);
		E_XMLFile_Dispatcher_values.BACKGROUND_IMAGE.addValue("Nature Theme", "Trees.jpg", false);
		E_XMLFile_Dispatcher_values.BACKGROUND_IMAGE.addValue("Cars Theme", "EQA.jpg", false);
		testSettingsField(E_XMLFile_Dispatcher_values.BACKGROUND_IMAGE.getValue("Dark Theme"), "BACKGROUND_IMAGE", "Sunset.jpg");
		testSettingsField(E_XMLFile_Dispatcher_values.BACKGROUND_IMAGE.getValue("Nature Theme"), "BACKGROUND_IMAGE", "Trees.jpg");
		testSettingsField(E_XMLFile_Dispatcher_values.BACKGROUND_IMAGE.getValue("Cars Theme"), "BACKGROUND_IMAGE", "EQA.jpg");
		
		// Replace single value by
		E_XMLFile_Dispatcher_values.BACKGROUND_IMAGE.setValues("Trees.jpg", "Lake.jpg", false);
		List<String> lstBI = Arrays.asList(E_XMLFile_Dispatcher_values.BACKGROUND_IMAGE.getValues());
		Boolean lakeFound = false;
		Boolean duplicates = false;
		for(int i=0; i<lstBI.size(); i++) {
			if(lstBI.get(i).equals("Lake.jpg")) {
				if(lakeFound == false) {
					lakeFound = true;
				}else {
					duplicates = true;
					break;
				}
			}
		}
		if((lakeFound) && (!duplicates)) {
			System.out.println("Success: BACKGROUND_IMAGE == Lake.jpg");
		} else if(duplicates){
			System.out.println("Failure: BACKGROUND_IMAGE multiple tags found with Lake.jpg");
		} else {
			System.out.println("Failure: BACKGROUND_IMAGE Lake.jpg not found");
		}
		
		// Replace all matching values
		E_XMLFile_Dispatcher_values.BACKGROUND_IMAGE.setValue("", "EQA.jpg","Lake.jpg");
		E_XMLFile_Dispatcher_values.BACKGROUND_IMAGE.setValues("Lake.jpg", "replaceAll.jpg", true);
		testSettingsField(E_XMLFile_Dispatcher_values.BACKGROUND_IMAGE.getValue("Dark Theme"), "BACKGROUND_IMAGE", "Sunset.jpg");
		testSettingsField(E_XMLFile_Dispatcher_values.BACKGROUND_IMAGE.getValue("Nature Theme"), "BACKGROUND_IMAGE", "replaceAll.jpg");
		testSettingsField(E_XMLFile_Dispatcher_values.BACKGROUND_IMAGE.getValue("Cars Theme"), "BACKGROUND_IMAGE", "replaceAll.jpg");
		
		E_XMLFile_Dispatcher_values.THEMES.delete();
	}

}
