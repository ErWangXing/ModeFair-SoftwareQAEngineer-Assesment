import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public class XeController {

    // Select the currency (Suitable From and to)
    // If exists currency, show currency elso show No available
    public static void currencySelection(WebDriver driver, WebDriverWait wait, String id,String currency){
        WebElement currencyBox = driver.findElement(By.xpath("//*[@id=\""+id+"\"]/div[2]/div/input"));

        currencyBox.click();

        currencyBox.sendKeys(currency);
        Pattern p = Pattern.compile("^["+currency+"]");

        try{
            wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\""+id+"-listbox\"]/*[@id=\""+id+"-option-0\"]/div[2]"), p));
            WebElement currencyOption = driver.findElement(By.xpath("//*[@id=\""+id+"-listbox\"]/*[@id=\""+id+"-option-0\"]/div[2]"));
            currencyOption.click();
            showCurrencyDescription(driver,id);
        }catch (Exception e){
            wait.until(ExpectedConditions.textToBe(By.cssSelector("#"+id+"-listbox li"), "No results available"));
            System.out.println(id+": " +driver.findElement(By.cssSelector("#"+id+"-listbox li")).getText());
        }

    }

    // Click Convert Button
    // * Show button Clickable or not
    // If clickable, show converted result else show No result
    public static void convertAction(WebDriver driver, WebDriverWait wait){
        WebElement convertButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[4]/div[2]/section/div[2]/div/main/div/div[2]/button"));
        System.out.println("Clickable Convert Button: "+convertButton.isEnabled());
        if ( convertButton.isEnabled() == true ){
            convertButton.click();
            WebElement fromCurrencyResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[4]/div[2]/section/div[2]/div/main/div/div[2]/div[1]/div/p")));
            WebElement ToCurrencyResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[4]/div[2]/section/div[2]/div/main/div/div[2]/div[1]/div/p[2]")));
            System.out.println("Result: "+fromCurrencyResult.getText() + " " + ToCurrencyResult.getText());
        }else{
            System.out.println("Result: -");
        }
    }

    // Show selected currency
    public static void showCurrencyDescription(WebDriver driver, String id){
        WebElement currencyDescription = driver.findElement(By.id(id+"-descriptiveText"));
        System.out.println(id+": "+currencyDescription.getText());
    }

    // Show Error message for Amount if have any
    public static void showErrorMessage(WebDriverWait wait){
        try{
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-live='assertive']")));
            System.out.println("Error Message: "+errorMessage.getText());
        }catch (Exception e){
            System.out.println("Error Message: -");
        }

    }
}
