import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteXPath {
	
	private CampoTreinamentoPage page;
	private WebDriver driver;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage(driver);
	}

	@After
	public void finaliza() {
		
	}

	@Test
	public void deveEncontrarElementosPeloXpath() {
		//também pode ser encontrado (//input)[6]
		driver.findElement(By.xpath("//input[@id='elementosForm:nome']")).sendKeys("Rayanne");
		//Pode também buscar pelo nome e depois acrescentar o value //*[@name='elementosForm:sexo' and @value='F']
		driver.findElement(By.xpath("//input[@value='F']")).click();
		driver.findElement(By.xpath("//input[@value='pizza']"));
		//Não pode usar o texto
		driver.findElement(By.xpath("//*[@id='tabelaSemJSF']//.td[.='Usuario B']"));
		driver.findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']//td[.='Maria']/..//input[@type='button']"));
		
		

	

	}
}
