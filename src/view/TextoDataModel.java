package view;

import java.util.List;

import javax.faces.model.ListDataModel;

import modal.Texto;

import org.primefaces.model.SelectableDataModel;

public class TextoDataModel extends ListDataModel<Texto> implements SelectableDataModel<Texto> {

	public TextoDataModel() {
	}
	
	public TextoDataModel( List<Texto> textosFormatados) {
		super(textosFormatados);
	}
	
	@Override
	public Texto getRowData(String rowKey) {
	     //List<Texto> textos = ((List<Texto>) getWrappedData());  
         
	       // for(Texto texto : textos) {  
	         //   if(texto.equals(rowKey))  
	           //     return texto;  
	        //}  
	          
	        return null; 
	}

	@Override
	public Object getRowKey(Texto texto) {
		return texto.getTexto();
	}
}
