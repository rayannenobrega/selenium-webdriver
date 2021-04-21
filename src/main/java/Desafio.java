import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Desafio {

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
	public void testeCadastro() {

		dsl.escreve("elementosForm:nome","Rayanne");
		dsl.escreve("elementosForm:sobrenome","Nóbrega");
		dsl.clicarRadio("elementosForm:sexo:1");
		dsl.clicarBotao("elementosForm:comidaFavorita:2");
		

		// Combo
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
	

		// Combo Multiplo
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
	

		// confirmação
		dsl.clicarBotao("elementosForm:cadastrar");
		
		// VALIDAÇÕES//
	
		Assert.assertEquals("Nome: Rayanne", dsl.obterTexto("descNome"));
		Assert.assertEquals("Sobrenome: Nóbrega", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Feminino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: O que eh esporte?", dsl.obterTexto("descEsportes"));

	}
}
