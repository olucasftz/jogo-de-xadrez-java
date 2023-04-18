package application;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrezgeral.PartidaDeXadrez;
import xadrezgeral.PecaDeXadrez;
import xadrezgeral.XadrezExcecao;
import xadrezgeral.XadrezPosicao;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaDeXadrez> capturada = new ArrayList<>();
		
		while (true) {
			try {
				UI.espacarTela();
				UI.exibirPartida(partidaDeXadrez, capturada);
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
				if(pecaCapturada != null) {
					capturada.add(pecaCapturada);
				}
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
