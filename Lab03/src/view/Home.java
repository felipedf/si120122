package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import threads.MostraArquivosThread;
import threads.ProcuraArquivosThread;
import util.FileSystem;
import util.JavaKeyWords;

public class Home extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static Home home;
	
	private JPanel pane;
	private JPanel tablePane;

	private JScrollPane scrollTable;

	private JLabel labelOutFiles;
	private JLabel labelOutPath;
	private JLabel labelAguarde;
	
	private JButton analisarButton;
	
	private JTable table;

	private FileSystem chooser;

	private String path;

	private ProcuraArquivosThread searchRun;
	private MostraArquivosThread filesThread;
	private ExecutorService executor;
	
	private boolean foiCancelado;
	
	public void init() {
		initComponents();
		if(foiCancelado) return;
		initThread();
		labelOutPath.setText(this.path);
		
		pane.add(this.labelOutPath);
		pane.add(this.labelAguarde);
		pane.add(this.labelOutFiles);
		pane.add(this.analisarButton);
		
		this.setContentPane(pane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setResizable(false);
		this.setVisible(true);
		
		endThread();
	}
	private Home() {
	}
	
	private void initThread() {
		
		searchRun = new ProcuraArquivosThread(path);
		filesThread = new MostraArquivosThread();
		
		executor = Executors.newFixedThreadPool(1);
		executor.execute(searchRun);
		
		filesThread.start();
	}
	
	private void endThread() {
		
		while(!this.searchRun.jaTerminou()) {
		}
		this.executor.shutdown();
		while(!this.executor.isShutdown()) {
		}
		this.filesThread.terminar();
	}
	
	public synchronized static Home getInstance(){
		if(home == null) {
			home = new Home();
		}
		return home;
	}

	private void populaTableModel(DefaultTableModel dtm) {
		JavaKeyWords keyWords = JavaKeyWords.getInstance();
		for (int i = 0; i < keyWords.getJavaKeyWords().length; i++) {
			dtm.addRow(new Object[]{"",keyWords.getJavaKeyWords()[i],0});
		}
	}

	private void initComponents() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.pane = new JPanel();
		this.tablePane = new JPanel();

		analisarButton = new JButton("Fazer outra busca");
		analisarButton.addActionListener(this);
		
		this.scrollTable = new JScrollPane();

		this.table = new JTable();

		this.chooser = new FileSystem();
		this.labelOutPath = new JLabel();
		this.labelAguarde = new JLabel();
		this.labelOutFiles = new JLabel("0 arquivos processados de 0 achados");
		
		this.path= "";
		this.foiCancelado = false;
		
		initTable();
		initFileChooser();

		getContentPane().add(pane, BorderLayout.CENTER);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width-800)/2, (screenSize.height-600)/2, 474, 283);

	}

	private void initTable() {
		getTable().setModel(new DefaultTableModel(
				new Object [][] { },
				new String [] {
						//aqui adiciona-se as colunas e seus respectivos nomes
						" ","Palavra Chave Java", "Numero de vezes"
				}
				));
		getTable().setEnabled(false);
		
		getTable().getColumnModel().getColumn(0).setPreferredWidth(5);
		getTable().getColumnModel().getColumn(0).setResizable(false);
		getTable().getColumnModel().getColumn(1).setPreferredWidth(150);
		getTable().getColumnModel().getColumn(1).setResizable(false);
		getTable().getColumnModel().getColumn(2).setPreferredWidth(150);
		getTable().getColumnModel().getColumn(2).setResizable(false);
		getTable().setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		scrollTable.setViewportView(getTable());
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		populaTableModel(dtm);

		tablePane.add(scrollTable);

		pane.add(tablePane);
		tablePane.setBounds(10, 10, 100, 100);
	}

	private void initFileChooser() {
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setBorder(new TitledBorder("Escolha o diretorio a ser analisado"));
		int retorno = chooser.showOpenDialog(pane);
		if(retorno == JFileChooser.APPROVE_OPTION) {
			path = chooser.getSelectedFile().getAbsolutePath();
			this.repaint();
		}
		else {
			this.foiCancelado = true;
		}
	}


	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	public JLabel getLabelOutFiles() {
		return labelOutFiles;
	}
	public void setLabelOutFiles(JLabel labelOutFiles) {
		this.labelOutFiles = labelOutFiles;
	}
	public JLabel getLabelAguarde() {
		return labelAguarde;
	}
	public void setLabelAguarde(JLabel labelAguarde) {
		this.labelAguarde = labelAguarde;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JavaKeyWords.reset();
		this.dispose();
		this.setVisible(false);
		this.init();
	}
}