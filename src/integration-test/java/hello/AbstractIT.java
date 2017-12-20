package hello;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public abstract class AbstractIT {

    @Autowired
    private Environment env;

    private static String SELENIUM_HUB_URL;
    protected static String TARGET_SERVER_URL;

    protected static WebDriver webDriver;

    @BeforeClass
    public static void initEnvironment() throws Exception {
        SELENIUM_HUB_URL = getConfigurationProperty(
           "SELENIUM_HUB_URL",
           "test.selenium.hub.url",
           "http://localhost:4444/wd/hub");

        TARGET_SERVER_URL = getConfigurationProperty(
            "TARGET_SERVER_URL",
            "test.target.server.url",
            "http://localhost:8080");

        webDriver = new RemoteWebDriver(getRemoteUrl(), getDesiredCapabilities());
    }

    private static String getConfigurationProperty(
            String envKey, String sysKey, String defValue) {
        String retValue = defValue;
        String envValue = System.getenv(envKey);
        String sysValue = System.getProperty(sysKey);
        // system property prevails over environment variable
        if (sysValue != null) {
            retValue = sysValue;
        } else if (envValue != null) {
            retValue = envValue;
        }
        return retValue;
    }

    private static DesiredCapabilities getDesiredCapabilities() {
        return DesiredCapabilities.chrome();
    }

    private static URL getRemoteUrl() throws MalformedURLException {
        return new URL(SELENIUM_HUB_URL);
    }
}
