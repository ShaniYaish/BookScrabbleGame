package ServerSide;

import java.util.*;

public class DictionaryManager {

    Map<String,Dictionary> dictionaryMap;

    private DictionaryManager() {
        this.dictionaryMap=new HashMap<>();
    }

    private static DictionaryManager dictionaryManager=null;
    public static DictionaryManager get(){//singleton
        if(dictionaryManager==null)
        {
            dictionaryManager=new DictionaryManager();
        }
        return dictionaryManager;
    }


    public boolean query(String...bookNames) {
        boolean isExist=false;
        for(String bookName : bookNames)
        {
            if(!this.dictionaryMap.containsKey(bookName) && bookName!=bookNames[bookNames.length-1])
            {
                dictionaryMap.put(bookName , new Dictionary(bookName));
            }
        }

        for(int i=0 ; i<bookNames.length-1 ; i++)
        {
            if(dictionaryMap.get(bookNames[i]).query(bookNames[bookNames.length-1]))
                isExist=true;
        }

        return isExist;
    }

    public boolean challenge(String...bookNames) {

        boolean isExist=false;
        for(String bookName : bookNames)
        {
            if(!this.dictionaryMap.containsKey(bookName) && bookName!=bookNames[bookNames.length-1])
            {
                dictionaryMap.put(bookName , new Dictionary(bookName));
            }
        }

        for(int i=0 ; i<bookNames.length-1; i++)
        {
            if(dictionaryMap.get(bookNames[i]).challenge(bookNames[bookNames.length-1]))
                isExist=true;
        }
        return isExist;
    }

    public int getSize()
    {
        return dictionaryMap.size();
    }
}
