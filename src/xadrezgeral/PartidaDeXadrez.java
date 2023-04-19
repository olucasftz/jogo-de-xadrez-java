package xadrezgeral;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleirogeral.Peca;
import tabuleirogeral.Posicao;
import tabuleirogeral.Tabuleiro;
import xadrezgeral.pecas.Bispo;
import xadrezgeral.pecas.Cavalo;
import xadrezgeral.pecas.Peao;
import xadrezgeral.pecas.Rainha;
import xadrezgeral.pecas.Rei;
import xadrezgeral.pecas.Torre;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean xeque;
	private boolean xequeMate;
	private PecaDeXadrez enPassantVulnerabilidade;

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

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean getXeque() {
		return xeque;
	}

	public boolean getXequeMate() {
		return xequeMate;
	}
	
	public PecaDeXadrez getEnPassantVulnerabilidade() {
		return enPassantVulnerabilidade;
	}

	public PecaDeXadrez[][] obterPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
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

		if (testarXeque(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new XadrezExcecao("Voce nao pode se colocar em Xeque");
		}

		PecaDeXadrez pecaMovimentada = (PecaDeXadrez) tabuleiro.peca(destino);
		
		xeque = (testarXeque(oponente(jogadorAtual))) ? true : false;

		if (testarXequeMate(oponente(jogadorAtual))) {
			xequeMate = true;
		} else {
			proximoTurno();
		}
		
		// #Movimento especial En Passant  - Testar se o Peao está vulnerável a tomar En passant
		if (pecaMovimentada instanceof Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			enPassantVulnerabilidade = pecaMovimentada;
		}
		else {
			enPassantVulnerabilidade = null;
		}

		return (PecaDeXadrez) pecaCapturada;
	}

	private Peca mover(Posicao origem, Posicao destino) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(origem);
		p.aumentarContagemDeMovimento();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.posicaoDaPeca(p, destino);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}

		// #Movimento especial lado menor do rei com a torre
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(origemTorre);
			tabuleiro.posicaoDaPeca(torre, destinoTorre);
			torre.aumentarContagemDeMovimento();
		}

		// #Movimento especial lado maior do rei com a torre
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(origemTorre);
			tabuleiro.posicaoDaPeca(torre, destinoTorre);;
			torre.aumentarContagemDeMovimento();
		}
		return pecaCapturada;
	}

	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(destino);
		p.diminuirContagemDeMovimento();
		tabuleiro.posicaoDaPeca(p, origem);

		if (pecaCapturada != null) {
			tabuleiro.posicaoDaPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
		
		// #Movimento especial lado menor do rei com a torre
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(destinoTorre);
			tabuleiro.posicaoDaPeca(torre, origemTorre);
			torre.diminuirContagemDeMovimento();
		}

		// #Movimento especial lado maior do rei com a torre
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(destinoTorre);
			tabuleiro.posicaoDaPeca(torre, origemTorre);;
			torre.diminuirContagemDeMovimento();
		}
		
	}

	private void validarPosicaoDeOrigem(Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezExcecao("Nao ha peca na posicao de origem");
		}
		if (jogadorAtual != ((PecaDeXadrez) tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezExcecao("A peca escolhida nao e sua");
		}
		if (!tabuleiro.peca(posicao).PossibilidadeDeMovimentacao()) {
			throw new XadrezExcecao("Nao existe possibilidade de movimentacao da peca selecionada");
		}
	}

	private void validarPosicaoDeDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).PossivelMovimentacao(destino)) {
			throw new XadrezExcecao("A peca selecionada nao pode se mover para a posicao de destino");
		}
	}

	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private PecaDeXadrez rei(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaDeXadrez) p;
			}
		}
		throw new IllegalStateException("Nao tem " + cor + " rei no tabuleiro");
	}

	private boolean testarXeque(Cor cor) {
		Posicao reiPosicao = rei(cor).obterXadrezPosicao().DaPosicao();
		List<Peca> pecasDoOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == oponente(cor))
				.collect(Collectors.toList());
		for (Peca p : pecasDoOponente) {
			boolean[][] mat = p.PossiveisMovimentacoes();
			if (mat[reiPosicao.getLinha()][reiPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testarXequeMate(Cor cor) {
		if (!testarXeque(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.PossiveisMovimentacoes();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((PecaDeXadrez) p).obterXadrezPosicao().DaPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = mover(origem, destino);
						boolean testarXeque = testarXeque(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testarXeque) {
							return false;
						}
					}
				}
			}
		}
		return true;
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
		colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));

		colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));
	}
}
