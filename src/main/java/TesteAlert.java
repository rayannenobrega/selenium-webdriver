import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {
	
	@Test
	public void deveInteragirComAlertSimples() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("alert")).click();
		//Pedindo pra o Selenium mudar o foco para o alerta e salvando o seu valor em uma vari�vel chamada alert do tipo Alert
		Alert alert = driver.switchTo().alert();
		//necess�rio pra pegar a msg que tem no alerta e conseguir escrever em outro componente
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", alert.getText());
		//precisa acionar o alerta pra sair da pag que � somente dele
		alert.accept();
		//enviando a msg de txt pra uma parte especifica do forms para testar a mensagem que foi pega
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		
		driver.quit();
						
	}
	
	@Test
	public void deveInteragirComAlertConfirm() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alert.getText());
		//Confirma��o do aceite
		alert.accept();
		Assert.assertEquals("Confirmado", alert.getText());
		alert.accept();
		//confirma��o do cancelar
		driver.findElement(By.id("confirm")).click();
		alert.dismiss();	
		Assert.assertEquals("Negado", alert.getText());
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).clear();
				
		driver.quit();
		
	}
	
	@Test
	public void deveInteragirComAlertPrompt() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.sendKeys("12");
		alert.accept();
		Assert.assertEquals("Era 12?", alert.getText());
		alert.accept();
		Assert.assertEquals(":D", alert.getText());
		alert.accept();
		
		driver.quit();
	}

}
