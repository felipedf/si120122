package view;

import java.util.List;

import modal.Recombinacoes;

public class RecombinaInvertido implements Recombinacoes {
	@Override
	public List<String> recombina(List<String> texto, int line) {
		texto.remove(line);
		return texto;
	}

	@Override
	public int linhaRecombinada(List<String> texto) {
		return texto.size()-1;
	}
}
