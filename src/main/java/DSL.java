import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;
	
	//construtor
	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	
	public void escreve (String id_campo, String texto) {
		driver.findElement(By.id(id_campo)).sendKeys(texto);
	}
	
	public String ObterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	public void clicarRadio(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isRadioMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void selecionarCombo(String id, String valor) {
		driver.findElement(By.id(id));
		// Salvando em uma variável do tipo "WebElement" que é a padrão desse tipo de código
		WebElement element = driver.findElement(By.id(id));
		// instanciando um novo objeto do tipo Select, que tem como valor um WebElement.
		Select combo = new Select(element);
		// O Select é um array, então o index é o índice do elemento desse array. Arrays
		// iniciam a contagem dos elementos a partir de 0.
		combo.selectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		// também é possível fazer o selectByValue. ex: combo.selectByValue("superior");
		// combo.getFirstSelectedOption().getText()= pega o texto do primeiro valor
		// selecionado pelo combo, que é um select.
		return combo.getFirstSelectedOption().getText();
	}
	
	public void clicarBotao(String id) {
		driver.findElement(By.id(id)).click();
	}
	public void clicarLink(String id) {
		driver.findElement(By.linkText(id)).click();
	}
		
	public String obterTexto(By by) {
		return  driver.findElement(by).getText();
	}

	public String obterTexto(String id) {
		return  obterTexto(By.id(id));
	}
}
