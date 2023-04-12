package xadrezgeral.pecas;

import tabuleirogeral.Tabuleiro;
import xadrezgeral.Cor;
import xadrezgeral.PecaDeXadrez;

public class Torre extends PecaDeXadrez{

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "T";
	}
	
}
