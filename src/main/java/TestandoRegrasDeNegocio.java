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
	public void deveValidarNomeObrigatorio() {

		driver.findElement(By.id("elementosForm:nome")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		// A mensagem � um alerta, n�o um pop-up
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		alert.accept();

	}

	@Test
	public void deveValidarSobrenomeObrigatorio() {

		driver.findElement(By.id("elementosForm:nome")).click();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rayanne");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		alert.accept();

	}

	@Test
	public void deveValidarSexoObrigatorio() {

		driver.findElement(By.id("elementosForm:nome")).click();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rayanne");
		driver.findElement(By.id("elementosForm:sobrenome")).click();
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("N�brega");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		alert.accept();

	}

	@Test
	public void deveValidarComida() {

		driver.findElement(By.id("elementosForm:nome")).click();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rayanne");
		driver.findElement(By.id("elementosForm:sobrenome")).click();
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("N�brega");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		alert.accept();

	}

	@Test
	public void deveValidarEsporte() {

		driver.findElement(By.id("elementosForm:nome")).click();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rayanne");
		driver.findElement(By.id("elementosForm:sobrenome")).click();
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("N�brega");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		alert.accept();

	}

}
