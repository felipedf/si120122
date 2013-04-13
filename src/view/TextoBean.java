package view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import modal.Recombinacoes;
import modal.Texto;

@ManagedBean
@SessionScoped
public class TextoBean {

	private List<Texto> textos;
	private Texto texto;
	private Recombinacoes recombinacao;
	private String rec;
	private String recombinando;
	private List<String> lines;
	private TextoDataModel textoData;
	private Texto textoSelecionado;
	
	private static final String INVERTER = "INVERTER";
	private static final String SEM_REPETICAO= "SEM_REPETICAO";
	private static final String COM_REPETICAO = "COM_REPETICAO";
	
	@PostConstruct
	public void init() {
		this.textos = new ArrayList<Texto>();
		this.lines = new ArrayList<String>();
		textoData = new TextoDataModel(this.textos);
		texto = new Texto("");
		setRecombinando("");
	}
	
	public String getTexto() {
		return this.texto.getTexto();
	}

	public void setTexto(String texto) {
		this.texto.setTexto(texto);
	}
	
	public String cadastrar() {
		Texto novo = new Texto(texto.getTexto());
		this.textos.add(novo);
		for(Texto texto : textos) {
			System.out.println(texto.getTexto());
		}
        FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage(null, new FacesMessage("Successful", "Hello "));
		return "/index?faces-redirect=true";
	}

	public List<Texto> getTextos() {
		return textos;
	}

	public void setTextos(List<Texto> textos) {
		this.textos = textos;
	}
	
	public String recombina() {
		if(rec.equals(COM_REPETICAO)) {
			setRecombinacao(new RecombinaRepetido());
		} else if(rec.equals(INVERTER)){
			setRecombinacao(new RecombinaInvertido());
		} else if(rec.equals(SEM_REPETICAO)) {
			setRecombinacao(new RecombinaNaoRepetido());
		} else {
			setRecombinacao(null);
		}
		setRecombinando("");
		lines = this.texto.inLines();
		return "/recombinando?faces-redirect=true";
	}

	public String getRec() {
		return rec;
	}

	public void setRec(String rec) {
		this.rec = rec;
	}
	
	public void adicionaLinha() {
		if( !lines.isEmpty()) {
			int line = recombinacao.linhaRecombinada(lines);
			setRecombinando(getRecombinando() + lines.get(line)+" ");
			this.lines = recombinacao.recombina(lines,line);
			texto.setTexto(getRecombinando());
		}
	}
	
	public void onRowSelect(SelectEvent event){
		texto = (Texto)event.getObject();
	}

	public TextoDataModel getTextoData() {
		return textoData;
	}

	public void setTextoData(TextoDataModel textoData) {
		this.textoData = textoData;
	}

	public Texto getTextoSelecionado() {
		return textoSelecionado;
	}

	public void setTextoSelecionado(Texto textoSelecionado) {
		this.textoSelecionado = textoSelecionado;
	}

	public Recombinacoes getRecombinacao() {
		return recombinacao;
	}

	public void setRecombinacao(Recombinacoes recombinacao) {
		this.recombinacao = recombinacao;
	}

	public String getRecombinando() {
		return recombinando;
	}

	public void setRecombinando(String recombinando) {
		this.recombinando = recombinando;
	}
}
