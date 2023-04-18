package xadrezgeral;

import tabuleirogeral.Tabuleiro;
import tabuleirogeral.Peca;
import tabuleirogeral.Posicao;

public abstract class PecaDeXadrez extends Peca{

	private Cor cor;
	private int contagemDeMovimento;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}	
	
	
	
	public int getContagemDeMovimento() {
		return contagemDeMovimento;
	}


	protected void aumentarContagemDeMovimento() {
		contagemDeMovimento++;
	}
	
	protected void diminuirContagemDeMovimento() {
		contagemDeMovimento--;
	}
	
	public XadrezPosicao obterXadrezPosicao() {
		return XadrezPosicao.paraPosicao(posicao);
	}
	
	protected boolean HaUmaPecaAdversaria(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}
}
