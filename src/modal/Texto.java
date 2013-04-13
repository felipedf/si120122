package modal;

import java.util.ArrayList;
import java.util.List;

public class Texto {

	private String texto;
	private String tipoRecombinacao;
	private String textoFormatado;
	
	private List<String> lines;
	
	public Texto(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTipoRecombinacao() {
		return tipoRecombinacao;
	}

	public void setTipoRecombinacao(String tipoRecombinacao) {
		this.tipoRecombinacao = tipoRecombinacao;
	}
	
	public void concatena(String str) {
		this.texto += str;
	}
	
	public String mostraTexto() {
		String aux = getTexto();
		System.out.println("MOSTRA "+aux);
		int textoSize = aux.length();
		for(int i=0,count=0; i<textoSize && count < 12; i++) {
			if( aux.charAt(i) == ' ' || aux.charAt(i) == '\n' || aux.charAt(i) == '\0') {
				count++;
			}
			if( aux.charAt(i) == '\n' ) 
				aux += " ";
			else aux += aux.charAt(i);
		}
		lines.add(aux);
		return aux;
	}
	
	public List<String> inLines() {
		lines = new ArrayList<String>();
		String aux = "";
		int len = 0;
		int textoSize = texto.length();
		while( len < textoSize) {
			for(int i=len,count=0; i<textoSize && count < 10; i++,len++) {
				if( texto.charAt(i) == ' ' || texto.charAt(i) == '\n' || texto.charAt(i) == '\0') {
					count++;
				}
				if( texto.charAt(i) == '\n' ) 
					aux += " ";
				else aux += texto.charAt(i);
			}
			lines.add(aux);
			aux = "";

		}
		return lines;
	}

	@Override
	public String toString() {
		return getTexto();
	}

	public String getTextoFormatado() {
		return textoFormatado;
	}

	public void setTextoFormatado(String textoFormatado) {
		this.textoFormatado = textoFormatado;
	}
}
