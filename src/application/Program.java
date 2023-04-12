package application;


import xadrezgeral.PartidaDeXadrez;

public class Program {

	public static void main(String[] args) {
		
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		UI.exibirTabuleiro(partidaDeXadrez.obterPecas());
	}

}
