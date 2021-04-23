import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

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
	public void testeCadastro() {

		page.setNome("Rayanne");
		page.setSobrenome("Nóbrega");
		page.setSexoFeminino();
		page.setComidaPizza();
		

		// Combo
		page.setEscolaridade("Superior");

		// Combo Multiplo
		page.setEsporte("O que eh esporte?");
	

		// confirmação
		page.cadastrar();
		
		// VALIDAÇÕES//
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Rayanne"));
		Assert.assertEquals("Sobrenome: Nóbrega", page.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Feminino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: O que eh esporte?", page.obterEsporteCadastro());

	}
}
