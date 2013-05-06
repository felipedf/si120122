package threads;

import java.awt.Dialog;
import java.util.Map;

import javax.sound.midi.SysexMessage;
import javax.swing.JDialog;

import util.JavaKeyWords;
import view.Home;

public class MostraArquivosThread extends Thread {

	private Home home;
	private JavaKeyWords keyWords;
	private boolean terminar;

	public MostraArquivosThread() {
		home = Home.getInstance();
		keyWords = JavaKeyWords.getInstance();
		terminar = false;
	}

	@Override
	public void run() {
		String key;
		Map<String, Integer> mapKey = keyWords.getMapKeyWords();
		try {
			while(!terminar) {
				for(int i=0; i < JavaKeyWords.javaKeys.length; i++) {
					key = JavaKeyWords.javaKeys[i];
					home.getTable().getModel().setValueAt(mapKey.get(key),i,2);			
				}

				Thread.sleep(400);
				showAguarde();
				Thread.sleep(450);
			}
		} catch (InterruptedException e) {
			atualiza();
			showConcluido();
		}
	}

	private void atualiza() {
		String key;
		Map<String, Integer> mapKey = keyWords.getMapKeyWords();
		for(int i=0; i < JavaKeyWords.javaKeys.length; i++) {
			key = JavaKeyWords.javaKeys[i];
			home.getTable().getModel().setValueAt(mapKey.get(key),i,2);			
		}
	}

	private void showConcluido() {
		home.getLabelAguarde().setText("                                 Concluido");
	}

	public void terminar() {
		this.terminar = true;
		this.interrupt();
	}

	private int count = 0;
	private void showAguarde() {
		String aguarde = "                                  Aguarde";
		count++;
		for (int i = 0; i < count; i++) {
			aguarde+=".";
		}
		count %= 3;
		home.getLabelAguarde().setText(aguarde);
	}
}