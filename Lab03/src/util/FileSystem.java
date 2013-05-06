package util;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Muda o texto Open do JFileChooser para Analisar.
 * 
 * @author Felipe
 *
 */
public class FileSystem extends JFileChooser{
	private static final long serialVersionUID = 1L;

	public FileSystem(){
        //texto para o JFileChooser
        UIManager.put ("FileChooser.openButtonText", "Analisar");
        SwingUtilities.updateComponentTreeUI(this);
         
    }
 
}