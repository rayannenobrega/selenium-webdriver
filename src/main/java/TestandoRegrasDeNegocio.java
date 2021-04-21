import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestandoRegrasDeNegocio {
	
	@Test
	public void deveValidarNomeObrigatorio() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		//A mensagem é um alerta, não um pop-up
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		alert.accept();
						
		driver.quit();
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).click();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rayanne");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		alert.accept();
						
		driver.quit();
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).click();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rayanne");
		driver.findElement(By.id("elementosForm:sobrenome")).click();
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nóbrega");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		alert.accept();
						
		driver.quit();
	}
	
	@Test
	public void deveValidarComida() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).click();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rayanne");
		driver.findElement(By.id("elementosForm:sobrenome")).click();
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nóbrega");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		alert.accept();
						
		driver.quit();
	}
	
	@Test
	public void deveValidarEsporte() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).click();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rayanne");
		driver.findElement(By.id("elementosForm:sobrenome")).click();
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nóbrega");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		alert.accept();
						
		driver.quit();
	}


}
