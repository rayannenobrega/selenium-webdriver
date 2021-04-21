import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrame {
	
	@Test
	public void deveInteragircomTextArea() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		//Mudando o foco para o frame (pegando pela tag do iframe)
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		//Salvando em uma variável
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Frame OK!", alert.getText());
		//salvando o texto em uma string
		String msg = alert.getText();
		alert.accept();
		//Mudando o foco novamente
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
				
		driver.quit();
	}
	
	
}
