package application;


import java.util.Scanner;

import xadrezgeral.PartidaDeXadrez;
import xadrezgeral.PecaDeXadrez;
import xadrezgeral.XadrezPosicao;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while (true) {
			UI.exibirTabuleiro(partidaDeXadrez.obterPecas());
			System.out.println();
			System.out.print("Posicao de origem: ");
			XadrezPosicao origem = UI.lerPosicaoDoXadrez(sc); 
			
			System.out.println();
			System.out.print("Posicao de destino: ");
			XadrezPosicao destino = UI.lerPosicaoDoXadrez(sc); 
			
			PecaDeXadrez pecaCapturada = partidaDeXadrez.executarJogadaDeXadrez(origem, destino);
		}
	}
}
