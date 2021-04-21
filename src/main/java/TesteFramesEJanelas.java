import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesEJanelas {
	
	@Test
	public void deveInteragircomFrames() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		//Mudando o foco para o frame (pegando pela tag do iframe)
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		//Salvando em uma vari�vel
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
	
	@Test
	public void deveInteragircomJanelas() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("buttonPopUpEasy")).click();
		//para trocar para um pop-up que se conhece o t�tulo
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		//� close e n�o quit, quit fecha tudo.
		driver.close();
		//voltando o foco para a janela principal que n�o possui t�tulo.
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
		
		driver.quit();
	}
	
	@Test
	public void deveInteragircomJanelasSemTitulos() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("buttonPopUpHard")).click();
		//com o windowhandle eu consigo pegar como se fosse o "ID" da janela.
		//no singular "windhowhandle" ele me devolveu o valor da janela principal, que estava em execu��o. No plural ele me trouxe a biblioteca, sendo o segundo valor o da pop-up.
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		//esses valores mudam a cada execu��o, ent�o n�o � bom usar eles como refer�ncia.
		//Por isso vamos passar o m�todo e transforma-lo em um array, pegando a segunda refer�ncia
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("deu certo?");
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
	
}
