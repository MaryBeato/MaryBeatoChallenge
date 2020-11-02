package TestCases;

import Pages.ContactForm;
import Pages.ContactMessageResponse;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class SendMessage_withoutEmail {

    private WebDriver driver;
    ContactForm form;
    ContactMessageResponse responseForm;
    Path resourceDirectory;
    String absolutePath;


    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        resourceDirectory = Paths.get("src","test","java","resources");
        absolutePath = resourceDirectory.toFile().getAbsolutePath();

    }
    @BeforeMethod
    public void goToUrl(){
        driver.get("http://automationpractice.com/index.php?controller=contact");
    }

    /**
     * This test case will send a message in the contact form completing avery field correctly
     *
     */
    @Test
    public  void sendMessageTest_inCorrectly() throws IOException {

        form = new ContactForm(driver);
        responseForm = new ContactMessageResponse(driver);

        String header = form.getLabelHeader();
        Assert.assertTrue(header.contains("CUSTOMER SERVICE - CONTACT US"));

        form.SendMessage("Customer service","","1234",absolutePath+"\\testfileImage.jpg","Test challenge");
        try {
            System.out.println(responseForm.getAlertErrorLabel());
            Assert.assertTrue(responseForm.getAlertErrorLabel().toLowerCase().contains("invalid email address"));
        } catch (Exception e) {
            Assert.assertFalse(responseForm.getAlertSuccesLabel().toLowerCase().contains("your message has been successfully sent to our team."));

        }


    }

    @AfterTest
    public void closedDriver(){

        this.driver.close();
        this.driver.quit();

    }


    @AfterMethod
    public void recordFailure(ITestResult result){

        if(ITestResult.FAILURE == result.getStatus())
        {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);


            try{
                Files.move(screenshot,
                        new File(absolutePath + "/screenshots/" + result.getName() + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


}
