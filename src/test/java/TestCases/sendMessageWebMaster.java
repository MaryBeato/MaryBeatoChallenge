package TestCases;


import Pages.ContactForm;
import Pages.ContactMessageResponse;
import Settings.Data;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class sendMessageWebMaster {


    private WebDriver driver;
    ContactForm form;
    ContactMessageResponse responseForm;
    Path resourceDirectory;
    String absolutePath;
    Data valMessage;
    List<String[]> dataMessage = null;
    String[] dMess = null;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        resourceDirectory = Paths.get("src","test","java","resources");
        absolutePath = resourceDirectory.toFile().getAbsolutePath();
        valMessage = new Data();
    }
    @BeforeMethod
    public void goToUrl(){
        driver.get("http://automationpractice.com/index.php?controller=contact");
        dataMessage = valMessage.getData(absolutePath+"\\dataSource.csv");
        dMess = dataMessage.get(7);
    }

     /**
     * This test case will send a message in the contact form completing avery field correctly but it will used the WebMaster type
     *
     */
    @Test
    public  void sendMessageWebMaster_correctly() throws IOException {

        form = new ContactForm(driver);
        responseForm = new ContactMessageResponse(driver);

        String header = form.getLabelHeader();
        Assert.assertTrue(header.contains("CUSTOMER SERVICE - CONTACT US"));
        String subject = dMess[0];
        String email =  dMess[1];
        String order =  dMess[2];
        String file =  dMess[3];
        String message =  dMess[4];

        form.setSubjectHeadingList(subject);
        System.out.println(form.getLabelWebMaster().toLowerCase());
        Assert.assertTrue(form.getLabelWebMaster().toLowerCase().contains("if a technical problem occurs on this"));

        form.setEmailTextBox(email);
        form.setOrderReferenceTextBox(order);
        form.setAttachBox(absolutePath+"\\"+file);
        form.setMessageBox(message);
        form.clickSendMessage();

        Assert.assertTrue(responseForm.getAlertSuccesLabel().toLowerCase().contains("your message has been successfully sent to our team."));

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
