package parallel_test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import supportfunc.SupportFunc;


import java.net.URL;
import java.util.concurrent.TimeUnit;

public class user {
    @Test
    public void user_test() throws InterruptedException {
        AppiumDriver<MobileElement> driver;
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("appWaitActivity", "xyz.medigo.user.ui.login.medigologin.MedigoLoginActivity");
//        user = SupportFunc.Connect_device(cap,"Google Pixel 3","10.0","binly_jlThrS","BFFviZkQQpdz9JbcvaYy","bs://2e8b898442583818805ff51a2c163a4654dd7ba1");
        driver = SupportFunc.Connect_emulator(cap,"emulator-5554", "xyz.medigo.user", "xyz.medigo.user.ui.login.LoginActivity");

        SupportFunc.Login_User(driver, "0987654321", "123456");
        SupportFunc.Change_Location_Emulator_To_VN(driver);

        MobileElement user11 = driver.findElementById("xyz.medigo.user:id/tab_medicine");
        user11.click();
        MobileElement user12 = driver.findElementById("xyz.medigo.user:id/imgIconClose");
        user12.click();

        MobileElement user13 = driver.findElementById("xyz.medigo.user:id/tvSearch");
        user13.click();
        MobileElement user14 = driver.findElementById("xyz.medigo.user:id/edtSearch");
        user14.sendKeys("do");
        Thread.sleep(500);
        driver.navigate().back();
        MobileElement user141 = driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().textContains(\"Đông Trùng Hạ Thảo\"))"));
        user141.click();
        MobileElement user142 = driver.findElementById("xyz.medigo.user:id/tvBuyNow");
        user142.click();

        MobileElement user20 = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"(COD)\")"));
        user20.click();
        MobileElement user21 = driver.findElementById("xyz.medigo.user:id/tvNext");
        user21.click();
        MobileElement user22 = driver.findElementById("xyz.medigo.user:id/tvOrderSucessView");
        user22.click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElementById("xyz.medigo.user:id/audio_btn_answer")));
        MobileElement user24 = driver.findElementById("xyz.medigo.user:id/audio_btn_answer");
        user24.click();

        WebDriverWait wait2 = new WebDriverWait(driver, 30);
        wait2.until(ExpectedConditions.visibilityOf(driver.findElementById("xyz.medigo.user:id/shipper_number")));
        driver.navigate().back();
        MobileElement user26 = driver.findElementById("xyz.medigo.user:id/tab_home");
        user26.click();
        driver.quit();
    }
}
