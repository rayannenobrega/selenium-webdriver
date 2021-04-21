import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class TesteCampoTreinamento {
	
	@Test
	public void testeTextField() {
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
						
		//driver.manage().window().setSize(new Dimension(1200,765));
		//driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de escrita",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
						
		driver.quit();
	}
	
	@Test
	public void deveInteragircomTextArea() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste");
		Assert.assertEquals("teste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
				
		driver.quit();
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		//Aqui eu estou verificando se um elemento está selecionado. No próprio isSelected ele me diz que retorna um booleano
		//Portanto, o assertTrue precisa ser de verificação de booleano.
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComCheckBox() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComCombo() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:escolaridade"));
		//Salvando em uma variável do tipo "WebElement" que é a padrão desse tipo de código
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		//instanciando um novo objeto do tipo Select, que tem como valor um WebElement.
		Select combo = new Select(element);
		//O Select é um array, então o index é o índice do elemento desse array. Arrays iniciam a contagem dos elementos a partir de 0.
		combo.selectByIndex(4);
		//também é possível fazer o selectByValue. ex: combo.selectByValue("superior");
		//combo.getFirstSelectedOption().getText()= pega o texto do primeiro valor selecionado pelo combo, que é um select.
		Assert.assertEquals("Superior",combo.getFirstSelectedOption().getText());
				
		driver.quit();
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:escolaridade"));
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
				
		boolean encontrou = false;
		for(WebElement option:options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
		
		driver.quit();
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		//Seleciona um elemento pelo texto visível
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		//mostra todos os elementos do combo, em forma de lista.
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		//deseleciona um elemento caso já esteja marcado. É necessário criar outra lista pra que funcione, pois a lista anterior não tem a deseleciou o elemento.
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
		
		driver.quit();
						
	}
	@Test
	public void deveInteragirComBotoes() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
		driver.quit();
						
	}
	
	@Test
	public void deveInteragirComLinks() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.linkText("Voltar")).click();
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
		
		driver.quit();
						
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		//Assim não é muito performático
		//Encontrou o body, pegou o texto completo do body e a partir desse texto eu verifico se tem essa string lá dentro.
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
		
		driver.quit();
						
	}
	
}
