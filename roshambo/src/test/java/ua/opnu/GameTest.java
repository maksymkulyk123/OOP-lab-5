package ua.opnu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void allNineCombinations() {
        GameShape[] shapes = {new Rock(), new Paper(), new Scissors()};
        int[][] expected = {
                {0, -1, 1},
                {1, 0, -1},
                {-1, 1, 0}
        };
        for (int player = 0; player < shapes.length; player++) {
            for (int computer = 0; computer < shapes.length; computer++) {
                assertEquals(expected[player][computer], MainFrame.checkWinner(shapes[player], shapes[computer]));
            }
        }
    }

    @Test
    void generatedShapeIsPlayable() {
        for (int i = 0; i < 100; i++) {
            GameShape shape = MainFrame.generateShape();
            assertTrue(shape instanceof Rock || shape instanceof Paper || shape instanceof Scissors);
        }
    }

    @Test
    void shapeNames() {
        assertEquals("Камінь", new Rock().toString());
        assertEquals("Папір", new Paper().toString());
        assertEquals("Ножиці", new Scissors().toString());
    }
}
