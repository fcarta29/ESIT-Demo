package hello;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HelloControllerIT.class)
public class HelloControllerIT extends AbstractIT {

    @Test
    public void visitIndexPage() throws Exception {

        try {
            webDriver.get(TARGET_SERVER_URL);
            WebElement body = webDriver.findElement(By.tagName("body"));

            Assert.assertThat(body.getText(), is(equalTo("Greetings from Spring Boot!")));
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }
}
