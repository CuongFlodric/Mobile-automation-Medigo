package parallel_test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import supportfunc.SupportFunc;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class phamacy {
    @Test
    public void pharmacy() throws InterruptedException {
        AppiumDriver<MobileElement> driver;
        DesiredCapabilities cap = new DesiredCapabilities();
//        driver = SupportFunc.Connect_device(cap,"Google Pixel 3","10.0","hngcngng_ybgou7","V4AbLYt29s1wK4NErZAv","bs://e5135a4962d9382e9ab04d13a44841b45f014296");
        driver = SupportFunc.Connect_emulator(cap,"emulator-5556", "xyz.medigo.pharmacy", "xyz.medigo.pharmacy.ui.login.loginpharcode.LoginPharCodeActivity");
        SupportFunc.Login_Phamacy(driver,"cuongmedi", "lalala@gmail.com","123123");

        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOf(driver.findElementById("xyz.medigo.pharmacy:id/btn_accept")));
        MobileElement pharmacy7 = driver.findElementById("xyz.medigo.pharmacy:id/btn_accept");
        pharmacy7.click();
        MobileElement pharmacy8 = driver.findElementById("xyz.medigo.pharmacy:id/button_call_free");
        pharmacy8.click();

        Thread.sleep(15000);

        MobileElement pharmacy10 = driver.findElementById("xyz.medigo.pharmacy:id/audio_btn_end");
        pharmacy10.click();
        MobileElement pharmacy11 = driver.findElementById("xyz.medigo.pharmacy:id/order_create");
        pharmacy11.click();

        MobileElement pharmacy12 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ImageView");

        Thread.sleep(3000);
        SupportFunc.swipeElementAndroid(pharmacy12,"RIGHT", (AndroidDriver) driver, 800);

        MobileElement pharmacy13 = driver.findElementById("xyz.medigo.pharmacy:id/btn_yes");
        pharmacy13.click();
        MobileElement pharmacy14 = driver.findElementById("xyz.medigo.pharmacy:id/tvViewOrder");
        pharmacy14.click();

        Thread.sleep(3000);
        driver.navigate().back();
        driver.quit();
    }
}


