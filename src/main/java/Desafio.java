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

	@Before
	public void inicializa() {
		driver = new ChromeDriver();

		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void testeCadastro() {

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rayanne");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nóbrega");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

		// Combo
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		combo.selectByIndex(4);

		// Combo Multiplo
		WebElement element2 = driver.findElement(By.id("elementosForm:esportes"));
		Select comboMultiplo = new Select(element2);
		comboMultiplo.selectByVisibleText("O que eh esporte?");

		// confirmação
		driver.findElement(By.id("elementosForm:cadastrar")).click();

		// VALIDAÇÕES//
		Assert.assertEquals("Nome: Rayanne", driver.findElement(By.id("descNome")).getText());
		Assert.assertEquals("Sobrenome: Nóbrega", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Feminino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: O que eh esporte?", driver.findElement(By.id("descEsportes")).getText());

	}
}
