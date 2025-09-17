package jogo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JogoTest {
    @Mock
    Jogador mockedJogador;

    @Mock
    Dado mockedDado1;

    @Mock
    Dado mockedDado2;

    Jogo jogo;

    @BeforeEach
    public void inicializa() {
        jogo = new Jogo(mockedDado1, mockedDado2, mockedJogador);
    }

    @Test
    public void testeVitoriaPrimeiroTurno() {
        when(mockedJogador.lancar(mockedDado1, mockedDado2)).thenReturn(7);

        boolean resultado = jogo.jogo();

        Assertions.assertTrue(resultado);
    }

    @Test
    public void testeDerrotaPrimeiroTurno() {
        // Resultado 2, 3 ou 12 no primeiro lançamento → derrota imediata
        when(mockedJogador.lancar(mockedDado1, mockedDado2)).thenReturn(2);

        boolean resultado = jogo.jogo();

        assertFalse(resultado);
    }

    @Test
    public void testePontoDepoisVitoria() {
        // Primeiro lançamento cria o ponto (por exemplo 4)
        // Segundo lançamento igual ao ponto → vitória
        when(mockedJogador.lancar(mockedDado1, mockedDado2))
                .thenReturn(4)  // primeiro lançamento
                .thenReturn(4); // segundo lançamento igual ao ponto

        boolean resultado = jogo.jogo();

        assertTrue(resultado);
    }

    @Test
    public void testePontoDepoisDerrota() {
        // Primeiro lançamento cria o ponto (por exemplo 5)
        // Segundo lançamento dá 7 → derrota
        when(mockedJogador.lancar(mockedDado1, mockedDado2))
                .thenReturn(5)  // primeiro lançamento → ponto
                .thenReturn(7); // segundo lançamento → derrota

        boolean resultado = jogo.jogo();

        assertFalse(resultado);
    }

    @Test
    public void testeVariosLancamentosAntesDoResultado() {
        // Primeiro lançamento cria o ponto 6
        // Lança 4, 5, 6 → vitória no último
        when(mockedJogador.lancar(mockedDado1, mockedDado2))
                .thenReturn(6)  // primeiro lançamento → ponto
                .thenReturn(4)  // segundo lançamento → continua
                .thenReturn(5)  // terceiro lançamento → continua
                .thenReturn(6); // quarto lançamento → acerta ponto → vitória

        boolean resultado = jogo.jogo();

        assertTrue(resultado);
    }

    @Test
    public void testeVariosLancamentosAntesDaDerrota() {
        // Primeiro lançamento cria o ponto 8
        // Lança 9, 5, 7 → derrota no último
        when(mockedJogador.lancar(mockedDado1, mockedDado2))
                .thenReturn(8)  // primeiro lançamento → ponto
                .thenReturn(9)  // segundo lançamento → continua
                .thenReturn(5)  // terceiro lançamento → continua
                .thenReturn(7); // quarto lançamento → derrota

        boolean resultado = jogo.jogo();

        assertFalse(resultado);
    }
}
