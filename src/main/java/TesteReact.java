import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteReact {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.get("https://iachallenge.phillrocha.com");
		dsl = new DSL(driver);

	}

	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void testaPaginaPhillippe(){
		
		driver.findElement(By.xpath("//input[@value='Pastel de queijo']/..")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Pastel de queijo']")).isSelected());
		
		
		
	}

}
