package ai.ravi.bowling.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowlingTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    private void rollMany(int n, int pins) {
        for (int i = 0; i < n; i++) {
            game.roll(pins);
        }
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5); //spare
    }

    private void rollStrike() {
        game.roll(10);
    }

    @Test
    void gutterGame() {
        final var n = 20;
        final var pins = 0;
        rollMany(n, pins);
        assertEquals(0, game.score());
    }

    @Test
    void allOnes() {
        rollMany(20, 1);
        assertEquals(20, game.score());
    }


    @Test
    void oneSpare() {
        rollSpare();
        game.roll(3);
        rollMany(17, 0);
        assertEquals(16, game.score());
    }

    @Test
    void oneStrike() {
        rollStrike();
        game.roll(3);
        game.roll(4);
        rollMany(16, 0);
        assertEquals(24, game.score());
    }

    @Test
    void perfectGame() {
        rollMany(12, 10);
        assertEquals(300, game.score());
    }
}
