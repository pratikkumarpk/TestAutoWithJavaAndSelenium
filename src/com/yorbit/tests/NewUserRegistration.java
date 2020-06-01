/**
 * @author Pakshi
 *
 */
package com.yorbit.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.yorbit.business.PermissionController;
import com.yorbit.business.UserRegistration;
import com.yorbit.utilities.Common;
import com.yorbit.utilities.FileReaderUtil;

public class NewUserRegistration extends BaseTest {
	
	@Test
	public void registerNewUserAndVerify() throws Exception {
		try {
			logger = extent.startTest("register New User and Verify");
			Map<String,String> data = new HashMap<String,String>();
			lauchApp();
			
			data = FileReaderUtil.readCSVFile(localDir+"/DataFiles/NewUserRegistration.csv", 0);
			PermissionController.acceptPermission(mobiledriver,logger);
			
			Common.acceptAlert(mobiledriver);
			logger.log(LogStatus.PASS,"Accepted Alert");
			
			UserRegistration.clickOnStartRegistration(mobiledriver,logger);
			UserRegistration.fillRegistration(mobiledriver,data,logger);
			UserRegistration.verifyRegistration(mobiledriver, data, "name");
			UserRegistration.verifyRegistration(mobiledriver, data, "email");
			UserRegistration.verifyRegistration(mobiledriver, data, "lang");
			UserRegistration.verifyRegistration(mobiledriver, data, "pwd");
			UserRegistration.verifyRegistration(mobiledriver, data, "username");
			closeAppAndEndSession();
		}catch (Exception e) {
			Assert.fail();
			closeAppAndEndSession();
			
		}
	}
}
