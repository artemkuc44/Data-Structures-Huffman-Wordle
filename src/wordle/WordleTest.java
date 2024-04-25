package wordle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project20280.interfaces.List;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordleTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream testIn = new ByteArrayInputStream("PLATE\n".getBytes());
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent)); // Redirects System.out to capture the output
        System.setIn(testIn); // Sets System.in to read from the provided input
    }

    @Test
    void testHints() {
        Wordle wordleA = new Wordle();
        String target, guess;
        String [] hints;

        target = "abbey";
        guess = "keeps";
        hints = wordleA.getHint(target, guess);
        assertEquals("[⬛, 🟨, ⬛, ⬛, ⬛]", Arrays.toString(hints));

        target = "abbey";
        guess = "kebab";
        hints = wordleA.getHint(target, guess);
        assertEquals( "[⬛, 🟨, 🟩, 🟨, 🟨]", Arrays.toString(hints));

        target = "abbey";
        guess = "babes";
        hints = wordleA.getHint(target, guess);
        assertEquals( "[🟨, 🟨, 🟩, 🟩, ⬛]", Arrays.toString(hints));

        target = "lobby";
        guess = "table";
        hints = wordleA.getHint(target, guess);
        assertEquals("[⬛, ⬛, 🟩, 🟨, ⬛]", Arrays.toString(hints));

        target = "ghost";
        guess = "pious";
        hints = wordleA.getHint(target, guess);
        assertEquals("[⬛, ⬛, 🟩, ⬛, 🟨]", Arrays.toString(hints));

        target = "ghost";
        guess = "slosh";
        hints = wordleA.getHint(target, guess);
        assertEquals("[⬛, ⬛, 🟩, 🟩, 🟨]", Arrays.toString(hints));


        target = "kayak";
        guess = "aorta";
        hints = wordleA.getHint(target, guess);
        assertEquals("[🟨, ⬛, ⬛, ⬛, 🟨]", Arrays.toString(hints));

        target = "kayak";
        guess = "kayak";
        hints = wordleA.getHint(target, guess);
        System.out.println(target + ", " + guess + ", " + hints);
        assertEquals("[🟩, 🟩, 🟩, 🟩, 🟩]", Arrays.toString(hints));

        target = "kayak";
        guess = "fungi";
        hints = wordleA.getHint(target, guess);
        System.out.println(target + ", " + guess + ", " + hints);
        assertEquals("[⬛, ⬛, ⬛, ⬛, ⬛]", Arrays.toString(hints));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut); // Resets System.out to its original state
        System.setIn(originalIn); // Resets System.in to its original state
    }


}

//lever guess revel

//abbey guess: dicey , skiey