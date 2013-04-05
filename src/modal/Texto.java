package modal;

public class Texto {

	private final String INVERTE = "INVERTE";
	private final String COM_REPETICAO = "COM_REPETICAO";
	private final String SEM_REPETICAO = "SEM_REPETICAO";
	
	private String texto;
	private Recombinacoes recombinacao;
	private String tipoRecombinacao;
	
	public Texto(String texto) {
		this.texto = texto;
	}
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	public void recombina() {
		recombinacao.recombina(texto);
	}

	public String getTipoRecombinacao() {
		return tipoRecombinacao;
	}

	public void setTipoRecombinacao(String tipoRecombinacao) {
		this.tipoRecombinacao = tipoRecombinacao;
	}

	public Recombinacoes getRecombinacao() {
		return recombinacao;
	}

	public void setRecombinacao(String recombinacao) {
		if(recombinacao.equals(INVERTE)) {
			this.recombinacao = new RecombinacaoInvertida();
		}
		else if(recombinacao.equals(COM_REPETICAO)) {
			this.recombinacao = new RecombinacaoComRepeticao();
		}
		else if(recombinacao.equals(SEM_REPETICAO)) {
			this.recombinacao = new RecombinacaoSemRepeticao();
		}
	}
	
	public void concatena(String str) {
		this.texto += str;
	}
}
