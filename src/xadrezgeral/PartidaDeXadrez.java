package xadrezgeral;

import tabuleirogeral.Peca;
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
		for(int i=0; i<tabuleiro.getLinhas(); i++) {
			for(int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	public PecaDeXadrez executarJogadaDeXadrez(XadrezPosicao posicaoDeOrigem, XadrezPosicao posicaoDoDestino) {
		Posicao origem = posicaoDeOrigem.DaPosicao();
		Posicao destino = posicaoDoDestino.DaPosicao();
		validarPosicaoDeOrigem(origem);
		Peca pecaCapturada = mover(origem, destino);
		return (PecaDeXadrez) pecaCapturada;
	}
	
	private Peca mover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.posicaoDaPeca(p, destino);
		return pecaCapturada;
	}
	
	private void validarPosicaoDeOrigem(Posicao posicao) {
		if(!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezExcecao("Nao ha peca na posicao de origem");
		}
		if (!tabuleiro.peca(posicao).PossibilidadeDeMovimentacao()) {
			throw new XadrezExcecao("Nao existe possibilidade de movimentacao da peca selecionada");
		}
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.posicaoDaPeca(peca, new XadrezPosicao(coluna, linha).DaPosicao());
	}
	
	private void iniciarConfiguracao() {
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.WHITE));
		colocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.WHITE));

		colocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.BLACK));
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.BLACK));
	}
}
