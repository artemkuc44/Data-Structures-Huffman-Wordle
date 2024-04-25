package wordle;

import org.junit.jupiter.api.Test;
import project20280.interfaces.Map;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HuffmanTest {
    String fileName = "wordle/resources/dictionary.txt";

    @Test
    void testHuffman() {
        String expected = "[f, fb, b, fbn, n, fbne, e, fbnejqxzvgsimp, j, jq, q, jqxz, x, xz, z, jqxzv, v, jqxzvg, g, jqxzvgs, s, jqxzvgsimp, i, imp, m, mp, p, fbnejqxzvgsimpltohdwkyruca, l, lt, t, ltohd, o, ohd, h, hd, d, ltohdwkyruca, w, wk, k, wky, y, wkyr, r, wkyruca, u, uc, c, uca, a]";

        Wordle game = new Wordle();
        List<String> wordList = game.readDictionary(fileName);
        //System.out.println(wordList);

        Huffman huff = new Huffman(String.join("",game.dictionary));
        huff.compress(wordList.toString());
        project20280.hashtable.ChainHashMap<String, String> code = huff.letterCodes;
        String actual = huff.huffmanTree.inorder().toString();

        //System.out.println(expected);
        assertEquals(expected, actual);
    }
}
