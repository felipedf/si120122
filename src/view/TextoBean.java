package view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modal.Recombinacoes;
import modal.Texto;

@ManagedBean
@SessionScoped
public class TextoBean {

	private List<Texto> textos;
	private Texto texto;
	private Recombinacoes rec;
	private Texto recombinando;
	
	public TextoBean() {
		this.textos = new ArrayList<Texto>();
		texto = new Texto("");
		recombinando = new Texto("");
	}
	
	public String getTexto() {
		return this.texto.getTexto();
	}

	public void setTexto(String texto) {
		this.texto.setTexto(texto);
	}
	
	public String cadastrar() {
		this.textos.add(texto);
		return "/index?faces-redirect=true";
	}

	public List<Texto> getTextos() {
		return textos;
	}

	public void setTextos(List<Texto> textos) {
		this.textos = textos;
	}
	
	public void recombina() {
		texto.recombina();
	}

	public Recombinacoes getRec() {
		return rec;
	}

	public void setRec(Recombinacoes rec) {
		this.rec = rec;
	}

	public String getRecombinando() {
		return recombinando.getTexto();
	}

	public void setRecombinando(Texto recombinando) {
		this.recombinando = recombinando;
	}
	
	public String adicionaLinha() {
		this.recombinando.concatena("oee");
		return "/recombinando?faces-redirect=true";
	}
	
	
}
