import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestandoRegrasDeNegocio {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void deveValidarNomeObrigatorio() {

		dsl.clicarBotao("elementosForm:cadastrar");
		// A mensagem é um alerta, não um pop-up
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		alert.accept();

	}

	@Test
	public void deveValidarSobrenomeObrigatorio() {

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rayanne");
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		alert.accept();

	}

	@Test
	public void deveValidarSexoObrigatorio() {

		dsl.escreve("elementosForm:nome", "Rayanne");
		dsl.escreve("elementosForm:sobrenome", "Nóbrega");
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		alert.accept();

	}

	@Test
	public void deveValidarComida() {

		dsl.escreve("elementosForm:nome", "Rayanne");
		dsl.escreve("elementosForm:sobrenome", "Nóbrega");
		dsl.clicarBotao("elementosForm:sexo:1");
		dsl.clicarBotao("elementosForm:comidaFavorita:0");
		dsl.clicarBotao("elementosForm:comidaFavorita:3");
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		alert.accept();

	}

	@Test
	public void deveValidarEsporte() {

		dsl.escreve("elementosForm:nome", "Rayanne");
		dsl.escreve("elementosForm:sobrenome", "Nóbrega");
		dsl.clicarBotao("elementosForm:sexo:1");
		dsl.selecionarCombo("elementosForm:esportes","Natacao");
		dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");
		dsl.clicarBotao("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		alert.accept();

	}

}
