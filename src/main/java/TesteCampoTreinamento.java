import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

	private WebDriver driver;
	// instânciando a DSL criada em outra classe.
	private DSL dsl;

	// antes de cada teste execute o conteúdo desse método
	@Before
	public void inicializa() {
		// WebDriver driver = new FirefoxDriver();
		driver = new ChromeDriver();
		// driver.manage().window().setSize(new Dimension(1200,765));
		// driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	// depois de cada teste execute o conteúdo desse método
	@After
	public void finaliza() {
	//	driver.quit();

	}

	@Test
	public void testeTextField() {
		dsl.escreve("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.ObterValorCampo("elementosForm:nome"));

	}

	@Test
	public void deveInteragircomTextArea() {
		dsl.escreve("elementosForm:sugestoes", "teste");
		Assert.assertEquals("teste", dsl.ObterValorCampo("elementosForm:sugestoes"));

	}

	@Test
	public void deveInteragirComRadioButton() {

		dsl.clicarRadio("elementosForm:sexo:0");
		// Aqui eu estou verificando se um elemento está selecionado. No próprio
		// isSelected ele me diz que retorna um booleano
		// Portanto, o assertTrue precisa ser de verificação de booleano.
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

	}

	@Test
	public void deveInteragirComCheckBox() {

		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:0"));

	}

	@Test
	public void deveInteragirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");

		Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));

	}

	@Test
	public void deveVerificarValoresCombo() {

		driver.findElement(By.id("elementosForm:escolaridade"));
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());

		boolean encontrou = false;
		for (WebElement option : options) {
			if (option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);

	}

	@Test
	public void deveVerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		// mostra todos os elementos do combo, em forma de lista.
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		// deseleciona um elemento caso já esteja marcado. É necessário criar outra
		// lista pra que funcione, pois a lista anterior não tem a deseleciou o
		// elemento.
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());

	}

	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));

	}

	@Test
	public void deveInteragirComLinks() {

		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));

	}

	@Test
	public void deveBuscarTextosNaPagina() {

		// Assim não é muito performático
		// Encontrou o body, pegou o texto completo do body e a partir desse texto eu
		// verifico se tem essa string lá dentro.
		// Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo
		// de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));

	}
	
	@Test
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value='Escrito via JS'");
	
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
		
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		
		
		
	}
	

}
