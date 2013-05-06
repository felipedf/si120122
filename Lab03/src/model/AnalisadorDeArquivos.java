package model;

import java.io.File;
import java.io.FileFilter;
import java.util.Vector;

public class AnalisadorDeArquivos {
	
	public static File[] buscaArquivosEmDiretorio(String caminhoDiretorio) {
		
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File file) {
				return (file.getName().endsWith(".java") || file.isDirectory());
			}
		};
		File dir = new File(caminhoDiretorio);
		
		return procuraPorArquivos(dir,filter);
	}

	private static File[] procuraPorArquivos(File dir, FileFilter filter) {
	    Vector<File> enc = new Vector<File>();  

	    File[] files = dir.listFiles(filter);
	    
	    if(files == null) {
	    	return new File[] {};
	    }
	    for (int i = 0; i < files.length; i++) {
	        if (files[i].isDirectory()) {
	            //Adiciona no Vector os arquivos encontrados dentro do subdiretorio:  
	            File[] recFiles = procuraPorArquivos(files[i], filter);  
	            for (int j = 0; j < recFiles.length; j++) {  
	                enc.addElement(recFiles[j]);  
	            }  
	        } else {  
	            //Adiciona no Vector o arquivo encontrado dentro de 'dir':  
	            enc.addElement(files[i]);  
	        }  
	    }
	      
	    //Transforma um Vector em um File[]:  
	    File[] encontrados = new File[enc.size()];  
	    for (int i = 0; i < enc.size(); i++) {  
	        encontrados[i] = (File)enc.elementAt(i);
	    }  
	    return encontrados;
	}
}