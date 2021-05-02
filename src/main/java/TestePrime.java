import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestePrime {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=5f049");
		dsl = new DSL(driver);

	}

	@After
	public void finaliza() {
		 driver.quit();
	}

	@Test
	public void deveInteragirComRadioPrime() {
		dsl.clicarRadio(By.xpath("//input[@id='j_idt303:console:0']/../..//span"));
		// Esse ID é dinamico, quando começar com esse j_idt e algum número, pode ficar com medo.
		//Se alguém fizer alguma alteração nessa tela que mude a estrutura dos elementos esse ID vai mudar
		Assert.assertTrue(dsl.isRadioMarcado("j_idt303:console:0"));
		dsl.clicarRadio(By.xpath("//label[.='Option2']/..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt303:console:1"));
	}
}
