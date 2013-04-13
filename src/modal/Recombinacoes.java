package modal;

import java.util.List;

public interface Recombinacoes {
	List<String> recombina(List<String> texto, int line);
	
	int linhaRecombinada(List<String> texto);
}
