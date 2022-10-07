package RegressionTest.ArchiveUtility;

import java.io.File;

import org.opentdk.api.util.ArchiveUtil;
import org.opentdk.api.util.ArchiveUtil.ArchiveCommand;

import RegressionTest.BaseRegression;

public class RT_ArchiveUtil_runProcess extends BaseRegression {

	public static void main(String[] args) {
		new RT_ArchiveUtil_runProcess();
	}
	
	@Override
	protected void runTest() {
		ArchiveUtil.getInstance().setFileNames("*.json *.yaml *.properties *.xml *.csv");
		ArchiveUtil.getInstance().setSwitches("-t7z -m0=BCJ2 -m1=LZMA:d25:fb255 -m2=LZMA:d19 -m3=LZMA:d19 -mb0:1 -mb0s1:2 -mb0s2:3 -mx");
		
		int success = ArchiveUtil.getInstance().runProcess("testdata\\RegressionTestData", "C:\\Program Files\\7-Zip\\7z.exe", "..\\RegressionTestData.7z");
		BaseRegression.testResult(success, "State", 1);
		BaseRegression.testResult(String.valueOf(new File("testdata/RegressionTestData.7z").exists()), "Compressed folder exists", "true");
		

		ArchiveUtil.getInstance().setSwitches("-o\"RegressionTestData_Extracted\" -t7z -y -x!*.cmd -x!config.txt -x!*.html");
		success = ArchiveUtil.getInstance().runProcess("testdata", "C:\\Program Files\\7-Zip\\7z.exe", "RegressionTestData.7z", ArchiveCommand.Extract, true);
		BaseRegression.testResult(success, "State", 0);
		BaseRegression.testResult(String.valueOf(new File("testdata/RegressionTestData_Extracted").exists()), "Extracted folder exists", "true");
	}

}
