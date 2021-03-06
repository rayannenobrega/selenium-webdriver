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
		page.setSobrenome("N?brega");
		page.setSexoFeminino();
		page.setComidaPizza();
		

		// Combo
		page.setEscolaridade("Superior");

		// Combo Multiplo
		page.setEsporte("O que eh esporte?");
	

		// confirma??o
		page.cadastrar();
		
		// VALIDA??ES//
		Assert.assertEquals("Rayanne", page.obterNomeCadastro());
		Assert.assertEquals("N?brega", page.obterSobrenomeCadastro());
		Assert.assertEquals("Feminino", page.obterSexoCadastro());
		Assert.assertEquals("Pizza", page.obterComidaCadastro());
		Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("O que eh esporte?", page.obterEsporteCadastro());

	}
}
