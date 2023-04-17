package application;


import java.util.InputMismatchException;
import java.util.Scanner;

import xadrezgeral.PartidaDeXadrez;
import xadrezgeral.PecaDeXadrez;
import xadrezgeral.XadrezExcecao;
import xadrezgeral.XadrezPosicao;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while (true) {
			try {
				UI.espacarTela();
				UI.exibirPartida(partidaDeXadrez);
				System.out.println();
				System.out.print("Posicao de origem: ");
				XadrezPosicao origem = UI.lerPosicaoDoXadrez(sc); 
				
				boolean[][] possiveisMovimentacoes = partidaDeXadrez.possiveisMovimentacoes(origem);
				UI.espacarTela();
				UI.exibirTabuleiro(partidaDeXadrez.obterPecas(), possiveisMovimentacoes);			
				System.out.println();
				System.out.print("Posicao de destino: ");
				XadrezPosicao destino = UI.lerPosicaoDoXadrez(sc); 
				
				PecaDeXadrez pecaCapturada = partidaDeXadrez.executarJogadaDeXadrez(origem, destino);
			} 
			catch (XadrezExcecao e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} 
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}
