package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BmiCalculator {

    public WebDriver driver;
    String weight;
    String growthCm;
    String growthFt1;
    String growthFt2;
    WebElement elementWeight;
    WebElement elementGrowthCm;
    WebElement elementGrowthFt1;
    WebElement elementGrowthFt2;
    WebElement elementResult;

    @BeforeClass
    public void setupBrowser(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://healthunify.com/bmicalculator/");
        elementWeight = driver.findElement(By.xpath("//input[@name = 'wg']"));
        elementGrowthCm = driver.findElement(By.xpath("//input[@name = 'ht']"));
        elementGrowthFt1 = driver.findElement(By.xpath("//select[@name='opt2']"));
        elementGrowthFt2 = driver.findElement(By.xpath("//select[@name='opt3']"));
        elementResult = driver.findElement(By.xpath("//input [@name='desc']"));
    }

    @Test
    public void veryfyStarvationKgTest() {
        weight = "40";
        growthCm = "175";
        elementWeight.sendKeys(weight);
        elementGrowthCm.sendKeys(growthCm);
        driver.findElement(By.xpath("//input[@name='cc']")).click();
        String starvationWeight = elementResult.getAttribute("value");
        String starvationExpRes = "Your category is Starvation";
        Assert.assertEquals( starvationWeight, starvationExpRes);

    }

    @Test
    public void veryfyUnderweightIbTest() {
        weight = "23";
        growthCm = "175";
        elementWeight.sendKeys(weight);
        driver.findElement(By.xpath("//select[@name='opt1']")).sendKeys("pounds");
        elementGrowthCm.sendKeys(growthCm);
        driver.findElement(By.xpath("//input[@name='cc']")).click();
        String underWeight = elementResult.getAttribute("value");
        String underWeightExpRes = "Your category is Underweight";
        Assert.assertEquals( underWeight, underWeightExpRes);

    }

    @Test
    public void veryfyNormalFtTest() {
        weight = "25";
        growthFt1 = "5";
        growthFt2 = "4";
        elementWeight.sendKeys(weight);
        driver.findElement(By.xpath("//select[@name='opt1']")).sendKeys("pounds");
        elementGrowthFt1.sendKeys(growthFt1);
        elementGrowthFt2.sendKeys(growthFt2);
        driver.findElement(By.xpath("//input[@name='cc']")).click();
        String normalWeight = elementResult.getAttribute("value");
        String normalWeightExpRes = "Your category is Normal";
        Assert.assertEquals(normalWeight, normalWeightExpRes);

    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();

    }


}
