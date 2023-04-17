package xadrezgeral.pecas;

import tabuleirogeral.Tabuleiro;
import xadrezgeral.Cor;
import xadrezgeral.PecaDeXadrez;

public class Rei extends PecaDeXadrez{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override 
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] PossiveisMovimentacoes() {
		boolean mat[][] = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return mat;
	}
}
