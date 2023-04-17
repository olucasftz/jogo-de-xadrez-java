package xadrezgeral;

import tabuleirogeral.Tabuleiro;
import tabuleirogeral.Peca;
import tabuleirogeral.Posicao;

public abstract class PecaDeXadrez extends Peca{

	private Cor cor;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}	
	
	protected boolean HaUmaPecaAdversaria(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}
}
