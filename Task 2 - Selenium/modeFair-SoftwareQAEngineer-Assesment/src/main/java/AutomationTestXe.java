import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class AutomationTestXe {
    // Set Constant value
    final static String websiteUrl = "https://www.xe.com/";
    final static String idFromCurrencyBox = "midmarketFromCurrency";
    final static String idToCurrencyBox = "midmarketToCurrency";

    public static void main(String[] args) {
        String amount = "abc";
        String fromCurrency = "MYR";
        String toCurrency = "SGD";

        // Amount = abc, From Currency = MYR, To Currency = SGD
        System.out.println("Scenario 1: Check input validation for Amount");
        scenarioInputValidationForAmount(amount,fromCurrency,toCurrency);


        // Amount = -1, From Currency = MYR, To Currency = SGD
        System.out.println("Scenario 2: Check negative number for Amount ");
        amount = "-1";
        scenarioInputValidationForAmount(amount,fromCurrency,toCurrency);

        // Amount = 10, From Currency = 123, To Currency = 123
        amount = "10";
        fromCurrency = "123";
        toCurrency = "123";
        System.out.println("Scenario 3: Check input validation for Currency");
        scenarioInputValidationForCountry(amount,fromCurrency,toCurrency);

        // Amount = abc, From Currency = MYR, To Currency = SGD
        amount = "10";
        fromCurrency = "MYR";
        toCurrency = "SGD";
        scenario4(amount,fromCurrency,toCurrency);
        scenario5(amount,fromCurrency,toCurrency);
    }

    public static void scenarioInputValidationForAmount(String amount, String fromCurrency, String toCurrency){
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10)); // Wait max 10 Seconds

        driver.get(websiteUrl);
        WebElement amountBox = driver.findElement(By.id("amount"));
        amountBox.sendKeys(amount);
        System.out.println("Amount: "+amount);

        XeController.currencySelection(driver,wait,idFromCurrencyBox, fromCurrency);
        XeController.currencySelection(driver,wait,idToCurrencyBox, toCurrency);

        XeController.convertAction(driver,wait);
        XeController.showErrorMessage(wait);
        driver.quit();
    }

    public static void scenarioInputValidationForCountry(String amount, String fromCurrency, String toCurrency){
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10)); // Wait max 10 Seconds


        driver.get(websiteUrl);
        WebElement amountBox = driver.findElement(By.id("amount"));
        amountBox.sendKeys(amount);
        System.out.println("Amount: "+amount);

        XeController.currencySelection(driver,wait,idFromCurrencyBox, fromCurrency);
        XeController.currencySelection(driver,wait,idToCurrencyBox, toCurrency);

        XeController.convertAction(driver,wait);
        XeController.showErrorMessage(wait);
        driver.quit();
    }

    public static void scenario4( String amount, String fromCurrency, String toCurrency){
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10)); // Wait max 10 Seconds

        System.out.println("Scenario 4: Convert MYR 10 to SGD");
        driver.get(websiteUrl);
        WebElement amountBox = driver.findElement(By.id("amount"));
        amountBox.sendKeys(amount);
        System.out.println("Amount: "+amount);

        XeController.currencySelection(driver,wait,idFromCurrencyBox, fromCurrency);
        XeController.currencySelection(driver,wait,idToCurrencyBox, toCurrency);

        XeController.convertAction(driver,wait);
        XeController.showErrorMessage(wait);

//        WebElement toCurrencyBox = driver.findElement(By.xpath("//*[@id=\"midmarketToCurrency\"]/div[2]/div/input"));
//
//        fromCurrencyBox.click();
//
//        fromCurrencyBox.sendKeys(fromCurrency);
//        Pattern p = Pattern.compile("^["+fromCurrency+"]");
//
//
//        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"midmarketFromCurrency-listbox\"]/*[@id=\"midmarketFromCurrency-option-0\"]/div[2]"), p));
//        WebElement fromCurrencyOption = driver.findElement(By.xpath("//*[@id=\"midmarketFromCurrency-listbox\"]/*[@id=\"midmarketFromCurrency-option-0\"]/div[2]"));
////        WebElement fromCurrencyOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"midmarketFromCurrency-listbox\"]")));
//        System.out.println(fromCurrencyOption.getText());
//        fromCurrencyOption.click();
//
//
//        toCurrencyBox.sendKeys(toCurrency);
//        p = Pattern.compile("^["+toCurrency+"]");
//
//        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"midmarketToCurrency-listbox\"]/*[@id=\"midmarketToCurrency-option-0\"]/div[2]"), p));
//        WebElement toCurrencyOption = driver.findElement(By.xpath("//*[@id=\"midmarketToCurrency-listbox\"]/*[@id=\"midmarketToCurrency-option-0\"]/div[2]"));
//        System.out.println(toCurrencyOption.getText());
//        toCurrencyOption.click();


//        WebElement convertButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[4]/div[2]/section/div[2]/div/main/div/div[2]/button"));
//        System.out.println("Convert Button Status: "+convertButton.isEnabled());
//        if ( convertButton.isEnabled() == true ){
//            convertButton.click();
//            WebElement fromCurrencyResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[4]/div[2]/section/div[2]/div/main/div/div[2]/div[1]/div/p")));
//            WebElement ToCurrencyResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[4]/div[2]/section/div[2]/div/main/div/div[2]/div[1]/div/p[2]")));
//
//            System.out.println(fromCurrencyResult.getText() + " " + ToCurrencyResult.getText());
//
//        }

        driver.quit();
    }

    public static void scenario5(String amount, String fromCurrency, String toCurrency){
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10)); // Wait max 10 Seconds

        System.out.println("Scenario 5: Switch currency from MYR =>SGD to SGD=>MYR ");
        driver.get(websiteUrl);
        WebElement amountBox = driver.findElement(By.id("amount"));
        amountBox.sendKeys(amount);
        System.out.println("Amount: "+amount);

        System.out.println("Before Switch: ");
        XeController.currencySelection(driver,wait,idFromCurrencyBox, fromCurrency);
        XeController.currencySelection(driver,wait,idToCurrencyBox, toCurrency);

        WebElement switchButton = driver.findElement(By.cssSelector("button[aria-label='Swap currencies']"));
        switchButton.click();

        System.out.println("After Switch: ");
        XeController.showCurrencyDescription(driver,idFromCurrencyBox);
        XeController.showCurrencyDescription(driver,idToCurrencyBox);

        XeController.convertAction(driver,wait);
        XeController.showErrorMessage(wait);
        driver.quit();
    }
}
