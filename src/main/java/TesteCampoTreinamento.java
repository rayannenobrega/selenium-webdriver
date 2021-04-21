import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

	private WebDriver driver;

	// antes de cada teste execute o conte�do desse m�todo
	@Before
	public void inicializa() {
		// WebDriver driver = new FirefoxDriver();
		driver = new ChromeDriver();
		// driver.manage().window().setSize(new Dimension(1200,765));
		// driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	// depois de cada teste execute o conte�do desse m�todo
	@After
	public void finaliza() {
		driver.quit();

	}

	@Test
	public void testeTextField() {

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

	}

	@Test
	public void deveInteragircomTextArea() {

		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste");
		Assert.assertEquals("teste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

	}

	@Test
	public void deveInteragirComRadioButton() {

		driver.findElement(By.id("elementosForm:sexo:0")).click();
		// Aqui eu estou verificando se um elemento est� selecionado. No pr�prio
		// isSelected ele me diz que retorna um booleano
		// Portanto, o assertTrue precisa ser de verifica��o de booleano.
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

	}

	@Test
	public void deveInteragirComCheckBox() {

		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());

	}

	@Test
	public void deveInteragirComCombo() {

		driver.findElement(By.id("elementosForm:escolaridade"));
		// Salvando em uma vari�vel do tipo "WebElement" que � a padr�o desse tipo de
		// c�digo
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		// instanciando um novo objeto do tipo Select, que tem como valor um WebElement.
		Select combo = new Select(element);
		// O Select � um array, ent�o o index � o �ndice do elemento desse array. Arrays
		// iniciam a contagem dos elementos a partir de 0.
		combo.selectByIndex(4);
		// tamb�m � poss�vel fazer o selectByValue. ex: combo.selectByValue("superior");
		// combo.getFirstSelectedOption().getText()= pega o texto do primeiro valor
		// selecionado pelo combo, que � um select.
		Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());

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

		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		// Seleciona um elemento pelo texto vis�vel
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");

		// mostra todos os elementos do combo, em forma de lista.
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		// deseleciona um elemento caso j� esteja marcado. � necess�rio criar outra
		// lista pra que funcione, pois a lista anterior n�o tem a deseleciou o
		// elemento.
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());

	}

	@Test
	public void deveInteragirComBotoes() {

		driver.findElement(By.id("elementosForm:esportes"));
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));

	}

	@Test
	public void deveInteragirComLinks() {

		driver.findElement(By.linkText("Voltar")).click();
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());

	}

	@Test
	public void deveBuscarTextosNaPagina() {

		// Assim n�o � muito perform�tico
		// Encontrou o body, pegou o texto completo do body e a partir desse texto eu
		// verifico se tem essa string l� dentro.
		// Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo
		// de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
				driver.findElement(By.className("facilAchar")).getText());

	}

}
