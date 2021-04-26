import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesEJanelas {

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
	public void deveInteragircomFrames() {

		// Mudando o foco para o frame (pegando pela tag do iframe)
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		// Salvando em uma vari�vel
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Frame OK!", alert.getText());
		// salvando o texto em uma string
		String msg = alert.getText();
		alert.accept();
		// Mudando o foco novamente
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);

	}
	
	@Test
	public void deveInteragirComFrameEscondido() {
		
		WebElement frame = driver.findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
		
	}

	@Test
	public void deveInteragircomJanelas() {

		driver.findElement(By.id("buttonPopUpEasy")).click();
		// para trocar para um pop-up que se conhece o t�tulo
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		// � close e n�o quit, quit fecha tudo.
		driver.close();
		// voltando o foco para a janela principal que n�o possui t�tulo.
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");

	}

	@Test
	public void deveInteragircomJanelasSemTitulos() {

		driver.findElement(By.id("buttonPopUpHard")).click();
		// com o windowhandle eu consigo pegar como se fosse o "ID" da janela.
		// no singular "windhowhandle" ele me devolveu o valor da janela principal, que
		// estava em execu��o. No plural ele me trouxe a biblioteca, sendo o segundo
		// valor o da pop-up.
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		// esses valores mudam a cada execu��o, ent�o n�o � bom usar eles como
		// refer�ncia.
		// Por isso vamos passar o m�todo e transforma-lo em um array, pegando a segunda
		// refer�ncia
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("deu certo?");
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");

	}

}
