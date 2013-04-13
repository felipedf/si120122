package view;

import java.util.List;

import modal.Recombinacoes;

public class RecombinaRepetido implements Recombinacoes {

	@Override
	public List<String> recombina(List<String> texto, int line) {
		return texto;
	}

	@Override
	public int linhaRecombinada(List<String> texto) {
		return (int) (Math.random()*texto.size());
	}
}