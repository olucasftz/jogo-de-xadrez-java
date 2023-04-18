package xadrezgeral.pecas;

import tabuleirogeral.Posicao;
import tabuleirogeral.Tabuleiro;
import xadrezgeral.Cor;
import xadrezgeral.PecaDeXadrez;

public class Rainha extends PecaDeXadrez{

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] PossiveisMovimentacoes() {
		boolean mat[][] = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		// Cima
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if(getTabuleiro().posicaoExistente(p) && HaUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Baixo
	
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if(getTabuleiro().posicaoExistente(p) && HaUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Esquerda
		
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if(getTabuleiro().posicaoExistente(p) && HaUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Direita
		
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if(getTabuleiro().posicaoExistente(p) && HaUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		// DIAGONAIS
		// Nordeste
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if(getTabuleiro().posicaoExistente(p) && HaUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Noroeste
	
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if(getTabuleiro().posicaoExistente(p) && HaUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Sudeste
		
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if(getTabuleiro().posicaoExistente(p) && HaUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Sudoeste
		
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if(getTabuleiro().posicaoExistente(p) && HaUmaPecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "Q";
	}
	
}
