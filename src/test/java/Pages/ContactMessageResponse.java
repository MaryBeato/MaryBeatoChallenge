package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactMessageResponse {

    private WebDriver driver;
    By AlertSuccesLabel = By.xpath("/html/body/div/div[2]/div/div[3]/div/p");
    By AlertErrorLabel = By.xpath("/html/body/div/div[2]/div/div[3]/div/div");

    public ContactMessageResponse(WebDriver driver){

        this.driver = driver;

    }

    public String getAlertSuccesLabel(){

        return    driver.findElement(AlertSuccesLabel).getText();

    }
    public String getAlertErrorLabel(){

        return    driver.findElement(AlertErrorLabel).getText();

    }


}
