import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

	private WebDriver driver;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();

	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void teste() {

		// driver.manage().window().setSize(new Dimension(1200,765));
		// driver.manage().window().maximize();
		driver.get("https://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());

	}

}
