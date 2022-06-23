package supportfunc;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SupportFunc {
    public static AppiumDriver<MobileElement> Connect_device(DesiredCapabilities cap, String deviceName, String os_version, String userName, String key, String appUrl){
        cap.setCapability("app", appUrl);        // Specify device and os_version for testing
        cap.setCapability("device", deviceName);
        cap.setCapability("os_version", os_version);
        // Set other BrowserStack capabilities
        cap.setCapability("project", "First Java Project");
        cap.setCapability("build", "Java Android");
        cap.setCapability("name", "first_test");
        cap.setCapability("browserstack.user", userName);
        cap.setCapability("browserstack.key", key);
        cap.setCapability("browserstack.networkLogs", true);
        try {
            URL url = new URL("http://hub.browserstack.com/wd/hub");
            AppiumDriver<MobileElement> driver = new AndroidDriver<MobileElement>(url, cap);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            return driver;
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("[ERR] Could not create new session");
        }

    }

    public static AppiumDriver<MobileElement> Connect_emulator(DesiredCapabilities cap, String emulator, String appPackage, String appActivity){
        cap.setCapability("automationName", "uiautomator2");        // Specify device and os_version for testing
        cap.setCapability("platformName", "android");
        cap.setCapability("udid", emulator);
        // Set other BrowserStack capabilities
        cap.setCapability("appPackage", appPackage);
        cap.setCapability("appActivity", appActivity);
        cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,true);

        try {
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            AppiumDriver<MobileElement> driver = new AndroidDriver<MobileElement>(url, cap);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            return driver;
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("[ERR] Could not create new session");
        }

    }

    public static void Login_User(AppiumDriver<MobileElement> driver, String phone_number, String password){
        MobileElement user2 = driver.findElementById("xyz.medigo.user:id/edtPhoneNumber");
        user2.sendKeys(phone_number);
        MobileElement user3 = driver.findElementById("xyz.medigo.user:id/btnGetSMSCode");
        user3.click();
        MobileElement user4 = driver.findElementById("xyz.medigo.user:id/txt_pin_entry");
        user4.sendKeys(password);
        MobileElement user5 = driver.findElementById("xyz.medigo.user:id/btnVerifySMSCode");
        user5.click();
    }

    public static void Login_Phamacy(AppiumDriver<MobileElement> driver, String phamacy_code, String email, String password){
        MobileElement pharmacy2 = driver.findElementById("xyz.medigo.pharmacy:id/login_phar_code");
        pharmacy2.sendKeys(phamacy_code);
        MobileElement pharmacy3 = driver.findElementById("xyz.medigo.pharmacy:id/continue_button");
        pharmacy3.click();
        MobileElement pharmacy4 = driver.findElementById("xyz.medigo.pharmacy:id/login_email");
        pharmacy4.sendKeys(email);
        MobileElement pharmacy5 = driver.findElementById("xyz.medigo.pharmacy:id/login_password");
        pharmacy5.sendKeys(password);
        MobileElement pharmacy6 = driver.findElementById("xyz.medigo.pharmacy:id/login_button");
        pharmacy6.click();
    }

    public static void Change_Location_Emulator_To_VN(AppiumDriver<MobileElement> driver){
        MobileElement user8 = driver.findElementById("xyz.medigo.user:id/home_address_location");
        user8.click();
        MobileElement user9 = driver.findElementById("xyz.medigo.user:id/tv_address");
        user9.click();
        MobileElement user10 = driver.findElementById("xyz.medigo.user:id/action_done");
        user10.click();
    }

    public static void swipeElementAndroid(MobileElement el, String dir, AndroidDriver driver, int s) {
        final int ANIMATION_TIME = 500; // ms

        final int PRESS_TIME = 500; // ms
        Rectangle rect = el.getRect();
        PointOption pointOptionStart, pointOptionEnd;
        pointOptionStart = PointOption.point(rect.x, rect.y);
        switch (dir) {
            case "DOWN": // from up to down
                pointOptionEnd = PointOption.point(rect.x , rect.y - s);
                break;
            case "UP": // from down to up
                pointOptionEnd = PointOption.point(rect.x , rect.y + s);
                break;
            case "LEFT": // from right to left
                pointOptionEnd = PointOption.point(rect.x - s, rect.y);
                break;
            case "RIGHT": // from left to right
                pointOptionEnd = PointOption.point(rect.x + s, rect.y);
                break;
            default:
                throw new IllegalArgumentException("swipeElementAndroid(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}
