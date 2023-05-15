package ServerSide;

import java.util.HashSet;

public class CacheManager {

    int cashSize; // Cache size
    CacheReplacementPolicy crp;
    HashSet<String> cacheWords;

    public CacheManager(int cashSize, CacheReplacementPolicy crp) {
        this.cashSize = cashSize;
        this.crp = crp;
        this.cacheWords = new HashSet<>();
    }

    public boolean query(String word){
        return cacheWords.contains(word);
    }

    public void add(String word){
        this.crp.add(word);
        if(cacheWords.size() >= cashSize)
        {
            this.cacheWords.remove(crp.remove());
        }

        cacheWords.add(word);
    }


}
