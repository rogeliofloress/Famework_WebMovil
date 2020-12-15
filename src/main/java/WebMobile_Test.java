import com.microsoft.appcenter.appium.Factory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;
*/

public class WebMobile_Test {
    public RemoteWebDriver driver;
    public static final String APPIUM_SRV_URL = "http://127.0.0.1:4723/wd/hub";
    public static final String DEVICE_NAME = "9553aea8";

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        dc.setCapability("platformName", "Android");
        dc.setCapability("deviceName", DEVICE_NAME);
        dc.setCapability("browserName", "Chrome");
        //driver = new RemoteWebDriver(new URL(APPIUM_SRV_URL), dc);
        driver = new AndroidDriver<MobileElement>(new URL(APPIUM_SRV_URL), dc);
    }

    @Test
    public void testWeb() throws InterruptedException, IOException {
        driver.get("http://www.intranet.itw.mx/login");

        By usr = By.xpath("//INPUT[@aria-label='nombre.apellido*']");
        By pass = By.xpath("//INPUT[@id='password']");
        By entr = By.xpath("//DIV[@class='v-btn__content'][text()='Entrar']");
        By menuH = By.xpath("//I[@data-v-359b1358=''][text()='menu']");
        By empld = By.xpath("//*[@id=\"tamaño\"]/div/div/div");
        By cncl = By.xpath("(//DIV[@class='v-btn__content'][text()='Cancelar'][text()='Cancelar'])[2]");
        By lgout = By.xpath("//I[@data-v-359b1358=''][text()='exit_to_app']");
        By accept = By.xpath("(//DIV[@class='v-btn__content'][text()='Aceptar'][text()='Aceptar'])[3]");

        takeScreenShot();
        driver.findElement(usr).click();
        driver.findElement(usr).sendKeys("rogelio.flores");
        driver.findElement(pass).click();
        driver.findElement(pass).sendKeys("F4cult4d#");
        takeScreenShot();
        driver.findElement(entr).click();
        Thread.sleep(2000);
        takeScreenShot();
        driver.findElement(menuH).click();
        takeScreenShot();
        driver.findElement(empld).click();
        Thread.sleep(2000);
        takeScreenShot();
        //driver.findElement(title).isDisplayed();
        driver.findElement(cncl).click();
        takeScreenShot();
        driver.findElement(lgout).click();
        takeScreenShot();
        driver.findElement(accept).click();
        Thread.sleep(3000);
        takeScreenShot();
    }

    public void takeScreenShot() throws IOException, InterruptedException {
        Thread.sleep(1000);
        // Set folder name to store screenshots.
        File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);


        String destDir = "C:\\Users\\FCDell\\Desktop\\Evidencias\\screen";
        // Capture screenshot.
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Set date format to set It as screenshot file name.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        // Create folder under project with name "screenshots" provided to destDir.
        new File(destDir).mkdirs();
        // Set file name using current date time.
        String destFile = dateFormat.format(new Date()) + ".png";
        try {
            // Copy paste file at destination folder location
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @After
    public void tearDown() throws MalformedURLException {
        driver.quit();
    }
}
