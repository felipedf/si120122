package threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import util.JavaKeyWords;
import view.Home;

import model.AnalisadorDeArquivos;

public class ProcuraArquivosThread implements Runnable {

	private JavaKeyWords javaKeyWords;
	private Home home;
	private String path;
	private File[] arquivosJava;
	private boolean terminou;

	public ProcuraArquivosThread(String path) {
		this.path = path;
		home = Home.getInstance();
		javaKeyWords = JavaKeyWords.getInstance();
		terminou = false;
	}

	@Override
	public void run() {
		this.procuraJavaKeyWords();
	}

	public void procuraJavaKeyWords() {
		arquivosJava = AnalisadorDeArquivos.buscaArquivosEmDiretorio(path);
		processaArquivos(arquivosJava);
	}

	private void processaArquivos(File[] arquivosJava) {
		for(int i=0; i<arquivosJava.length; i++) {
			String line;
			try{
				BufferedReader in = new BufferedReader(new FileReader(arquivosJava[i]));
				while(in.ready()) {
					line = in.readLine();
					procuraPalavrasJava(line);
				}
				in.close();
				try {
					for(int k=0; k<javaKeyWords.getJavaKeyWords().length; k++) {
						String key = javaKeyWords.getJavaKeyWords()[k];
						home.getLabelOutFiles().setText((i+1)+" arquivos processados de "+arquivosJava.length+" achados");
					}
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
			}
			catch(IOException e){
			}
		}
		this.terminou = true;
	}

	private void procuraPalavrasJava(String line) {
		String wordkey; 

		line = line.replaceAll("[(,),;,{,}]", " ");

		String[] words = line.split(" ");
		for (int i = 0; i < words.length; i++) {
			wordkey = words[i].trim();
			if(javaKeyWords.getMapKeyWords().containsKey(wordkey)) {
				javaKeyWords.incrementaKeyWord(wordkey);
			}
		}
	}

	public boolean jaTerminou() {
		System.out.println();
		return terminou;
	}
}
