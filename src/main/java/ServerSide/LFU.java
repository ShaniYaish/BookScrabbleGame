package ServerSide;

import java.util.LinkedHashMap;
import java.util.Map;

public class LFU implements  CacheReplacementPolicy{

    LinkedHashMap<String ,Integer> wordMap;


    public LFU() {
        this.wordMap = new LinkedHashMap<>();
    }

    public void add(String word){
        if(wordMap.containsKey(word))
        {
            wordMap.replace(word , wordMap.get(word)+1);
        }

        else
            wordMap.put(word , 1);
    }

    public String remove(){

        String minWord = wordMap.keySet().iterator().next();
        int minCount = wordMap.get(minWord);

        for(Map.Entry<String , Integer> w : wordMap.entrySet())
        {
            if(w.getValue() < minCount)
            {
                minWord= w.getKey();
                minCount= w.getValue();
            }
        }

        return minWord;
    }
}
