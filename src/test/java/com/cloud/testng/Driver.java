package com.cloud.testng;

import com.testvagrant.optimuscloud.dashboard.testng.OptimusCloudConstants;
import com.testvagrant.optimuscloud.entities.MobileDriverDetails;
import com.testvagrant.optimuscloud.remote.OptimusCloudDriver;
import com.testvagrant.optimuscloud.remote.OptimusCloudManager;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;

public class Driver {

    protected MobileDriver<?> driver;
    private MobileDriverDetails mobileDriverDetails;
    private OptimusCloudManager optimusCloudManager;
    private WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void createDriver(ITestContext iTestContext) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.ticktick.task");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.ticktick.task.activity.MeTaskActivity");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/nishant/Development/myOwn/MyNoteBook/app/TickTick.apk");
        optimusCloudManager = new OptimusCloudManager();
        mobileDriverDetails = new OptimusCloudDriver().createDriver(desiredCapabilities);
        iTestContext.setAttribute(OptimusCloudConstants.MOBILE_DRIVER, mobileDriverDetails);
        driver = mobileDriverDetails.getMobileDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 20);
    }

    void waitForElementsToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        optimusCloudManager.releaseSession(mobileDriverDetails);
    }
}
