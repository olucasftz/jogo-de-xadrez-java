package xadrezgeral;

import tabuleirogeral.Posicao;
import tabuleirogeral.Tabuleiro;
import xadrezgeral.pecas.Rei;
import xadrezgeral.pecas.Torre;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		iniciarConfiguracao();
	}
	
	public PecaDeXadrez[][] obterPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i = 0; i < tabuleiro.getLinhas(); i++) {
			for(int j = 0; i < tabuleiro.getColunas(); i++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	private void iniciarConfiguracao() {
		tabuleiro.posicaoDaPeca(new Torre(tabuleiro, Cor.WHITE), new Posicao(2, 5));
		tabuleiro.posicaoDaPeca(new Rei(tabuleiro, Cor.BLACK), new Posicao(0, 4));
		tabuleiro.posicaoDaPeca(new Rei(tabuleiro, Cor.WHITE), new Posicao(7, 4));

	}
}
