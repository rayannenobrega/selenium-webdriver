import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	private WebDriver driver;

	// construtor
	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	/**** TextField e TextArea ****/

	public void escrever(By by, String texto) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}

	public void escreve(String id_campo, String texto) {
		escrever(By.id(id_campo), texto);
	}

	public String ObterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}

	/**** Radio e Check ****/

	public void clicarRadio(By by) {
		driver.findElement(by).click();
	}
	
	public void clicarRadio(String id) {
		clicarRadio(By.id(id));
	}

	public boolean isRadioMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}

	public void clicarCheck(String id) {
		driver.findElement(By.id(id)).click();
	}

	public boolean isCheckMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}

	/**** Combo ****/

	public void selecionarCombo(String id, String valor) {
		driver.findElement(By.id(id));
		// Salvando em uma vari�vel do tipo "WebElement" que � a padr�o desse tipo de
		// c�digo
		WebElement element = driver.findElement(By.id(id));
		// instanciando um novo objeto do tipo Select, que tem como valor um WebElement.
		Select combo = new Select(element);
		// O Select � um array, ent�o o index � o �ndice do elemento desse array. Arrays
		// iniciam a contagem dos elementos a partir de 0.
		combo.selectByVisibleText(valor);
	}

	public void deselecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}

	public String obterValorCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		// tamb�m � poss�vel fazer o selectByValue. ex: combo.selectByValue("superior");
		// combo.getFirstSelectedOption().getText()= pega o texto do primeiro valor
		// selecionado pelo combo, que � um select.
		return combo.getFirstSelectedOption().getText();
	}

	public List<String> obterValoresCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for (WebElement opcao : allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}

	public int obterQuantidadeOpcoesCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}

	public boolean verificarOpcaoCombo(String id, String opcao) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for (WebElement option : options) {
			if (option.getText().equals(opcao)) {
				return true;
			}
		}
		return false;

	}

	/**** Bot�o ****/

	public void clicarBotao(String id) {
		driver.findElement(By.id(id)).click();
	}

	public String obterValueElemento(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}

	/**** Link ****/

	public void clicarLink(String id) {
		driver.findElement(By.linkText(id)).click();
	}

	/**** Textos ****/

	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}

	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}

	/**** Alerts ****/

	public String alertaObterTexto() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public String alertaObterTextoEAceita() {
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
	}

	public String alertaObterTextoENega() {
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
	}

	public void alertaEscrever(String valor) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}

	/**** Frames e Janelas ****/

	public void entrarFrame(String id) {
		driver.switchTo().frame(id);
	}

	public void sairFrame(String id) {
		driver.switchTo().defaultContent();
	}

	public void trocarJanela(String id) {
		driver.switchTo().window(id);
	}
	
	/**** JS ****/
	
	public Object executarJS(String cmd, Object...param) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(cmd, param);
		
		
	}
	
	/**** Tabela ****/
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		//procurar coluna do registro
		WebElement tabela = driver.findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
				
		//encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		//procurar coluna do bot�o
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no bot�o da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
		
	}

	private int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement>linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for(int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	private int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for(int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}

}
