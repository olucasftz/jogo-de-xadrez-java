package xadrezgeral;

import java.util.ArrayList;
import java.util.List;

import tabuleirogeral.Peca;
import tabuleirogeral.Posicao;
import tabuleirogeral.Tabuleiro;
import xadrezgeral.pecas.Rei;
import xadrezgeral.pecas.Torre;

public class PartidaDeXadrez {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		iniciarConfiguracao();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getjogadorAtual() {
		return jogadorAtual;
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
	
	public boolean[][] possiveisMovimentacoes(XadrezPosicao posicaoDeOrigem) {
		Posicao posicao = posicaoDeOrigem.DaPosicao();
		validarPosicaoDeOrigem(posicao);
		return tabuleiro.peca(posicao).PossiveisMovimentacoes();
	}
	
	public PecaDeXadrez executarJogadaDeXadrez(XadrezPosicao posicaoDeOrigem, XadrezPosicao posicaoDoDestino) {
		Posicao origem = posicaoDeOrigem.DaPosicao();
		Posicao destino = posicaoDoDestino.DaPosicao();
		validarPosicaoDeOrigem(origem);
		validarPosicaoDeDestino(origem, destino);
		Peca pecaCapturada = mover(origem, destino);
		proximoTurno();
		return (PecaDeXadrez) pecaCapturada;
	}
	
	private Peca mover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.posicaoDaPeca(p, destino);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}		
		return pecaCapturada;
	}
	
	private void validarPosicaoDeOrigem(Posicao posicao) {
		if(!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezExcecao("Nao ha peca na posicao de origem");
		}
		if(jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezExcecao("A peca escolhida nao e sua");
		}
		if (!tabuleiro.peca(posicao).PossibilidadeDeMovimentacao()) {
			throw new XadrezExcecao("Nao existe possibilidade de movimentacao da peca selecionada");
		}
	}
	
	private void validarPosicaoDeDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.peca(origem).PossivelMovimentacao(destino)) {
			throw new XadrezExcecao("A peca selecionada nao pode se mover para a posicao de destino");
		}
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.posicaoDaPeca(peca, new XadrezPosicao(coluna, linha).DaPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private void iniciarConfiguracao() {
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		colocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
