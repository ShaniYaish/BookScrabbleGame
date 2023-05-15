package ServerSide;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class IOSearcher {


    static boolean search(String word , String...fileNames){

        Stream<String> s;

        for(int i=0 ; i < fileNames.length ; i++)
        {

            try {
                s = Files.lines(Paths.get(fileNames[i]));
                if(s.filter(line->line.contains(word)).count() != 0)
                    return true;
                s.close();
            } catch (IOException e) {
                System.err.println("Error with the file");
            }
        }


        return false;
    }
}
