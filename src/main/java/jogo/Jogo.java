package jogo;

public class Jogo {
	private Dado dado1;
	private Dado dado2;
	private Jogador jogador;
	public Jogo(Dado dado1, Dado dado2, Jogador jogador){
		this.dado1 = dado1;
		this.dado2 = dado2;
		this.jogador = jogador;
	}
	public boolean jogo(){ 
		int resultado = jogador.lancar(dado1, dado2);
		if(resultado == 7 || resultado == 11) { return true; }
		else if(resultado == 2 || resultado == 3 || resultado == 12){ return false; }
		int ponto = resultado;
		resultado = jogador.lancar(dado1, dado2);
		while(resultado != ponto && resultado != 7){
			resultado = jogador.lancar(dado1, dado2);
		}
		return resultado == ponto;
	}
}