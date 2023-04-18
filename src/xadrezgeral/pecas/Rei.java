package xadrezgeral.pecas;

import tabuleirogeral.Posicao;
import tabuleirogeral.Tabuleiro;
import xadrezgeral.Cor;
import xadrezgeral.PecaDeXadrez;

public class Rei extends PecaDeXadrez{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	private boolean podeMover(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] PossiveisMovimentacoes() {
		boolean mat[][] = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		// Norte
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Sul
		
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Leste
		
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Oeste
		
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Nordeste
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Noroeste
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Sudeste
		
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Sudoeste
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
	
	@Override 
	public String toString() {
		return "R";
	}
}
