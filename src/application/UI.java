package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrezgeral.Cor;
import xadrezgeral.PartidaDeXadrez;
import xadrezgeral.PecaDeXadrez;
import xadrezgeral.XadrezPosicao;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static void espacarTela() {
		System.out.println();
		System.out.println("-----------------");
		System.out.println();
	}
	
	public static XadrezPosicao lerPosicaoDoXadrez(Scanner sc) {
		try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new XadrezPosicao(coluna, linha);
		} 
		catch (RuntimeException e) {
			throw new InputMismatchException("erro ao ler uma Posicao do Xadrez. Os valores validos sao de a1 ao h8");
		}
	}
	
	public static void exibirPartida(PartidaDeXadrez partidaDeXadrez, List<PecaDeXadrez> capturada) {
		exibirTabuleiro(partidaDeXadrez.obterPecas());
		System.out.println();
		exibirPecasCapturadas(capturada);
		System.out.println();
		System.out.println("Turno: " + partidaDeXadrez.getTurno());
		
		if (!partidaDeXadrez.getXequeMate()) {
			System.out.println("Esperando Jogador: " + partidaDeXadrez.getJogadorAtual());
			if (partidaDeXadrez.getXeque()) {
				System.out.println("XEQUE!");
			}
		}
		else {
			System.out.println("XEQUEMATE!");
			System.out.println("Vencedor: " + partidaDeXadrez.getJogadorAtual());
		}
	}
	
	public static void exibirTabuleiro(PecaDeXadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				exibirPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void exibirTabuleiro(PecaDeXadrez[][] pecas, boolean[][] possiveisMovimentacoes) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				exibirPeca(pecas[i][j], possiveisMovimentacoes[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	public static void exibirPeca(PecaDeXadrez peca, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	private static void exibirPecasCapturadas(List<PecaDeXadrez> capturada) {
		List<PecaDeXadrez> branco = capturada.stream().filter(x -> x.getCor() == Cor.BRANCO).collect(Collectors.toList());
		List<PecaDeXadrez> preto = capturada.stream().filter(x -> x.getCor() == Cor.PRETO).collect(Collectors.toList());
		System.out.println("Pecas Capturadas: ");
		System.out.print("Pecas Brancas: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(branco.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print("Pecas Pretas:  ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(preto.toArray()));
		System.out.print(ANSI_RESET);
	}
}