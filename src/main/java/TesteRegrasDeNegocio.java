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

public class TesteRegrasDeNegocio {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void deveValidarNomeObrigatorio() {

		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());

	}

	@Test
	public void deveValidarSobrenomeObrigatorio() {

		page.setNome("Rayanne");
		page.cadastrar();

		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());

	}

	@Test
	public void deveValidarSexoObrigatorio() {

		page.setNome("Rayanne");
		page.setSobrenome("Nóbrega");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());

	}

	@Test
	public void deveValidarComida() {

		page.setNome("Rayanne");
		page.setSobrenome("Nóbrega");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());

	}

	@Test
	public void deveValidarEsporte() {

		page.setNome("Rayanne");
		page.setSobrenome("Nóbrega");
		page.setSexoFeminino();
		page.setEsporte("Karate", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());

	}

}
