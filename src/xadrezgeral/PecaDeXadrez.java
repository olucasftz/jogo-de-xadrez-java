package xadrezgeral;

import tabuleirogeral.Tabuleiro;
import tabuleirogeral.Peca;

public abstract class PecaDeXadrez extends Peca{

	private Cor cor;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}	
}
