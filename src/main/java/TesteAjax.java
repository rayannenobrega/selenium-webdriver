import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteAjax {

	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=e0d78");
		dsl = new DSL(driver);
		
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void testAjax() {
		dsl.escreve("j_idt302:name", "Teste");
		dsl.clicarBotao("j_idt302:j_idt306");
		//Passa o driver e o tempo de espera desejado
		WebDriverWait wait = new WebDriverWait(driver,30);
		//passa a condição que se espera. Ele pede primeiro um by e o valor que se espera, no nosso caso é teste.
		//wait.until(ExpectedConditions.textToBe(By.id("j_idt302:display"), "Teste"));
		
		
		//Esperando até que o elemento de carregamento fique invisível
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt345_start")));
		Assert.assertEquals("Teste",dsl.obterTexto("j_idt302:display"));
		
		}

}
