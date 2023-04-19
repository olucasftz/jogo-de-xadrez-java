package xadrezgeral.pecas;

import tabuleirogeral.Posicao;
import tabuleirogeral.Tabuleiro;
import xadrezgeral.Cor;
import xadrezgeral.PartidaDeXadrez;
import xadrezgeral.PecaDeXadrez;

public class Peao extends PecaDeXadrez{

	private PartidaDeXadrez partidadeDeXadrez;
	
	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidadeDeXadrez = partidaDeXadrez;
	}
	
	@Override
	public boolean[][] PossiveisMovimentacoes() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		if (getCor() == Cor.BRANCO) {
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().temUmaPeca(p2) && getContagemDeMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(p) && HaUmaPecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}			
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExistente(p) && HaUmaPecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}	
			
            // #Movimento especial En Passant peças Brancas
			if (posicao.getLinha() == 3) {
				Posicao paraEsquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExistente(paraEsquerda) && HaUmaPecaAdversaria(paraEsquerda) && getTabuleiro().peca(paraEsquerda) == partidadeDeXadrez.getEnPassantVulnerabilidade()) {
					mat[paraEsquerda.getLinha() - 1][paraEsquerda.getColuna()] = true;
				}
				Posicao paraDireita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExistente(paraDireita) && HaUmaPecaAdversaria(paraDireita) && getTabuleiro().peca(paraDireita) == partidadeDeXadrez.getEnPassantVulnerabilidade()) {
					mat[paraDireita.getLinha() - 1][paraDireita.getColuna()] = true;
				}
			}
		}
		else {
			p.setValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().temUmaPeca(p2) && getContagemDeMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExistente(p) && HaUmaPecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}			
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(p) && HaUmaPecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
            // #Movimento especial En Passant peças Pretas
			if (posicao.getLinha() == 4) {
				Posicao paraEsquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExistente(paraEsquerda) && HaUmaPecaAdversaria(paraEsquerda) && getTabuleiro().peca(paraEsquerda) == partidadeDeXadrez.getEnPassantVulnerabilidade()) {
					mat[paraEsquerda.getLinha() + 1][paraEsquerda.getColuna()] = true;
				}
				Posicao paraDireita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExistente(paraDireita) && HaUmaPecaAdversaria(paraDireita) && getTabuleiro().peca(paraDireita) == partidadeDeXadrez.getEnPassantVulnerabilidade()) {
					mat[paraDireita.getLinha() + 1][paraDireita.getColuna()] = true;
				}
			}
		}
		return mat;
	}
	
	@Override 
	public String toString() {
		return "P";
	}
	

}
