package ServerSide;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dictionary {

    private static final CacheReplacementPolicy LRU = new LRU();
    private static final CacheReplacementPolicy LFU = new LFU();

    CacheManager existWords;
    CacheManager nonExistWords;
    BloomFilter bloomFilter;

    String[] fileNamesArr;

    public Dictionary(String...fileNames) {

        this.fileNamesArr = fileNames.clone();
        this.existWords = new CacheManager(400,LRU);
        this.nonExistWords = new CacheManager(100 , LFU);
        this.bloomFilter = new BloomFilter(256,"MD5","SHA1");
        for(int i=0 ; i< fileNames.length ;i++)
        {
            insertWordToBF(fileNames[i]);
        }

    }

    public boolean query(String word)
    {
        if(existWords.query(word))
            return true;
        if (nonExistWords.query(word))
            return false;
        else
        if(bloomFilter.contains(word))
        {
            existWords.add(word);
            return true;
        }
        else
        {
            nonExistWords.add(word);
            return false;
        }
    }

    public boolean challenge(String word)
    {
        try {
            if (IOSearcher.search(word, fileNamesArr)) {
                existWords.add(word);
                return true;
            } else {
                nonExistWords.add(word);
                return false;
            }
        }catch (Exception e) {
            return false;}
    }

    public void insertWordToBF(String fileName)
    {

        try {
            Stream<String> s = Files.lines(Paths.get(fileName));
            Scanner scanner= new Scanner(s.collect(Collectors.joining()));
            while(scanner.hasNext())
                this.bloomFilter.add(scanner.next());
        } catch (Exception e) {
            System.err.println("Error with the file");
        }
    }

    //new File(Paths.get(fileName).toUri())

}
