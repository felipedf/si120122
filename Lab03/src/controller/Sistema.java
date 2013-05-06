package controller;

import view.Home;

public class Sistema {
	
	public static void main(String[] args) {
			//instancia um objeto do tip o JFrame
			Home janela = Home.getInstance();
			janela.init();
	}
}
