package ServerSide;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class LRU implements  CacheReplacementPolicy {

    LinkedHashSet<String> wordSet;

    public LRU() {
        this.wordSet = new LinkedHashSet<>();
    }

    public void add(String word) {

        wordSet.remove(word);
        wordSet.add(word);
    }


    public String remove() {
        String tmp = null;
        if (!wordSet.isEmpty()) {
            tmp = wordSet.iterator().next();
            wordSet.remove(tmp);
        }

        return tmp;

    }
}