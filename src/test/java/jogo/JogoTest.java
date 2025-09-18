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
    public void testeVitoriaPrimeiroTurnoCom7() {
        when(mockedJogador.lancar(mockedDado1, mockedDado2)).thenReturn(7);

        boolean resultado = jogo.jogo();

        Assertions.assertTrue(resultado);
    }
    @Test
    public void testeVitoriaPrimeiroTurnoCom11() {
        when(mockedJogador.lancar(mockedDado1, mockedDado2)).thenReturn(11);

        boolean resultado = jogo.jogo();

        Assertions.assertTrue(resultado);
    }

    @Test
    public void testeDerrotaPrimeiroTurnoCom2() {
        when(mockedJogador.lancar(mockedDado1, mockedDado2)).thenReturn(2);

        boolean resultado = jogo.jogo();

        assertFalse(resultado);
    }
    @Test
    public void testeDerrotaPrimeiroTurnoCom3() {
        when(mockedJogador.lancar(mockedDado1, mockedDado2)).thenReturn(3);

        boolean resultado = jogo.jogo();

        assertFalse(resultado);
    }
    @Test
    public void testeDerrotaPrimeiroTurnoCom12() {
        when(mockedJogador.lancar(mockedDado1, mockedDado2)).thenReturn(12);

        boolean resultado = jogo.jogo();

        assertFalse(resultado);
    }

    @Test
    public void testePontoDepoisVitoria() {
        when(mockedJogador.lancar(mockedDado1, mockedDado2))
                .thenReturn(4)
                .thenReturn(4);

        boolean resultado = jogo.jogo();

        assertTrue(resultado);
    }

    @Test
    public void testePontoDepoisDerrota() {
        when(mockedJogador.lancar(mockedDado1, mockedDado2))
                .thenReturn(5)
                .thenReturn(7);

        boolean resultado = jogo.jogo();

        assertFalse(resultado);
    }

    @Test
    public void testeVariosLancamentosAntesDoResultado() {
        when(mockedJogador.lancar(mockedDado1, mockedDado2))
                .thenReturn(6)
                .thenReturn(4)
                .thenReturn(5)
                .thenReturn(6);

        boolean resultado = jogo.jogo();

        assertTrue(resultado);
    }

    @Test
    public void testeVariosLancamentosAntesDaDerrota() {
        when(mockedJogador.lancar(mockedDado1, mockedDado2))
                .thenReturn(8)
                .thenReturn(9)
                .thenReturn(5)
                .thenReturn(7);

        boolean resultado = jogo.jogo();

        assertFalse(resultado);
    }
}
