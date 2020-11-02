package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class ContactForm {

    private WebDriver driver;
    By MessageBox = By.name("message");
    By SubjectHeadingList = By.name("id_contact");
    By EmailTextBox = By.id("email");
    By OrderReferenceTextBox = By.name("id_order");
    By AttachBox = By.id("fileUpload");
    By SendBtn = By.id("submitMessage");
    By ChooseFileBtn = By.xpath("/html/body/div/div[2]/div/div[3]/div/form/fieldset/div[1]/div[1]/p[5]/div/span[2]");
    By Label1Subject = By.id("desc_contact1");
    By Label2Subject = By.id("desc_contact2");
    By LabelHeader = By.xpath("/html/body/div/div[2]/div/div[3]/div/h1");

    public ContactForm(WebDriver driver){

        this.driver = driver;

    }
    public String getLabelHeader(){

        return    driver.findElement(LabelHeader).getText();

    }
    public String getLabelCustomerServ(){

        return    driver.findElement(Label2Subject).getText();

    }
    public String getLabelWebMaster(){

        return    driver.findElement(Label1Subject).getText();

    }

    public void setEmailTextBox(String email){

        driver.findElement(EmailTextBox).sendKeys(email);

    }

    public void setMessageBox(String message){

        driver.findElement(MessageBox).sendKeys(message);

    }
    public void setOrderReferenceTextBox(String ordenumber){

        driver.findElement(OrderReferenceTextBox).sendKeys(ordenumber);

    }

    public void setSubjectHeadingList(String optionList){

        Select droplist = new Select(driver.findElement(SubjectHeadingList));
        droplist.selectByVisibleText(optionList);

    }
    public void setAttachBox(String file){

        driver.findElement(AttachBox).sendKeys(file);
        // click the "UploadFile" button
      //  driver.findElement(AttachBox).sendKeys(Keys.ENTER);

    }

    public void clickSendMessage(){

        driver.findElement(SendBtn).click();

    }

    public void SendMessage(String subject,String email, String order, String file, String message){

        this.setSubjectHeadingList(subject);
        this.setEmailTextBox(email);
        this.setOrderReferenceTextBox(order);
        this.setAttachBox(file);
        this.setMessageBox(message);

        this.clickSendMessage();
    }

}
