package Template.Application;

import java.util.List;
import java.util.logging.Level;

import org.opentdk.api.application.BaseApplication;
import org.opentdk.api.dispatcher.BaseDispatchComponent;
import org.opentdk.api.dispatcher.BaseDispatcher;
import org.opentdk.api.logger.MLogger;

import RegressionTest.BaseRegression;

import java.io.IOException;
import java.lang.reflect.Field;

public class Application extends BaseApplication {

	public static void main(String[] args) throws Exception {
		new Application(args);
	}
	
	public Application(String[] args) throws Exception {
		System.out.println(args[0]);
		System.out.println(ERuntimeProperties.HOMEDIR.getValue());
		parseArgs(ERuntimeProperties.class, args);	
		System.out.println(ERuntimeProperties.HOMEDIR.getValue());
	}

//	private void parseArgs(String[] args) {		
//		// Initialize the ERuntimeProperties class
//		try {
//			BaseDispatcher.setDataContainer(ERuntimeProperties.class, "./conf/AppSettings_1.xml", ERuntimeProperties.HOMEDIR.getRootNode());
//		} catch (IOException e) {
//			MLogger.getInstance().log(Level.WARNING, e);
//		}
//		// Get all fields of the ERuntimeProperties
//		List<Field> propertyFields = BaseDispatcher.getFields(ERuntimeProperties.class);
//		for (String arg : args) {
//
//			String key = arg.split("=")[0];
//			String value = arg.split("=")[1];
//
//			for (Field fld : propertyFields) {
//				if (fld.getName().equalsIgnoreCase(key)) {
//					
//					// Get the runtime object to execute the method with
//					Object fieldInstance = null;
//					try {
//						fieldInstance = fld.get(ERuntimeProperties.class);
//					} catch (IllegalArgumentException | IllegalAccessException e1) {
//						e1.printStackTrace();
//					}
//					
//					if(fieldInstance != null) {
//						if(fieldInstance instanceof BaseDispatchComponent) {
//							((BaseDispatchComponent) fieldInstance).setValue(value);
//						}
//					}
//				}
//			}
//		}
//		BaseRegression.testResult(ERuntimeProperties.SETTINGSFILE.getValue(), "New settings file", "conf/TestSettings.xml");
//
//	}
}
